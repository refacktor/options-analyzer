package com.spxvol.www.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.spxvol.math.BlackScholes;
import com.spxvol.math.BlackScholes.Indicator;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.model.Constants;
import com.spxvol.www.model.StandardQuote;
import com.spxvol.www.services.DataUploadService;

@Controller
public class DataUploadController {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private final DataUploadService dataUploadService;

	private final ObjectMapper mapper = JsonMapper.builder().addModule(new ParameterNamesModule()).addModule(new Jdk8Module())
			.addModule(new JavaTimeModule()).build();
	
	public DataUploadController(DataUploadService dataUploadService) {
		super();
		this.dataUploadService = dataUploadService;
	}

	@PostMapping("/internal/upload")
	public ResponseEntity<String> upload(@RequestBody String json) throws Exception {
		final JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		final JsonNode all = rootNode.get("stock").get("All");
		
		Underlying underlying = dataUploadService.underlying(symbol);
		underlying.setLastTrade(Instant.ofEpochSecond(all.get("timeOfLastTrade").asLong()));
		underlying.setPrice(all.get("lastTrade").asDouble());
		
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		List<OptionQuote> results = new ArrayList<>();
		rootNode.withArray("options").elements().forEachRemaining(optionsNodes -> {
			JsonNode optionChainResponseNode = optionsNodes.get("OptionChainResponse");
		
			JsonNode expirationNode = optionsNodes.get("Expiration");
			LocalDate expiration = LocalDate.of(expirationNode.get("year").asInt(),
					expirationNode.get("month").asInt(), expirationNode.get("day").asInt());
		
			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				JsonNode[] optionPairNodes = { optionPairNode.get("Call"), optionPairNode.get("Put") };
				for(JsonNode optionNode: optionPairNodes) {

					OptionQuote build = mapper.convertValue(optionNode, OptionQuote.class);
					build.setExpiration(expiration);
				
					JsonNode greeks = optionNode.get("OptionGreeks");
					build.setRho(greeks.get("rho").asDouble(0));
					build.setVega(greeks.get("vega").asDouble(0));
					build.setTheta(greeks.get("theta").asDouble(0));
					build.setDelta(greeks.get("delta").asDouble(0));
					build.setGamma(greeks.get("gamma").asDouble(0));
					build.setIv(greeks.get("iv").asDouble(0));
                     
					if(build.getIv() < 0.0) {
						build.setIv(0.0);
					}
					
					final Indicator typeCode = Indicator.valueOf(build.getOptionType().substring(0,1));
					double timeToExpiry = expiration.toEpochDay() - underlying.getLastTrade()
							.atZone(Constants.MARKET_TIME_ZONE).toLocalDate().toEpochDay();
					double riskFreeRate = 0.01;
					
					build.setBidIV(BlackScholes.reverse(build.getBid().doubleValue(), typeCode, timeToExpiry, underlying.getPrice(), build.getStrikePrice().doubleValue(), riskFreeRate));
					build.setAskIV(BlackScholes.reverse(build.getAsk().doubleValue(), typeCode, timeToExpiry, underlying.getPrice(), build.getStrikePrice().doubleValue(), riskFreeRate));
					
					results.add(build);
				}
			});
		});
		final List<OptionQuote> quotes = results;
		dataUploadService.saveAll(symbol, quotes);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println(runningTime + " millisecs (" + (runningTime / 1000.0) + ")secs");
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}

	@PostMapping("/internal/uploadV2")
	public ResponseEntity<String> upload(@RequestBody StandardQuote data) throws Exception {
		String symbol = data.getUnderlying().getSymbol();
		Underlying underlying = dataUploadService.underlying(symbol);
		underlying.setLastTrade(data.getUnderlying().getLastTrade());
		underlying.setPrice(data.getUnderlying().getPrice());
		
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		List<OptionQuote> results = new ArrayList<>();
		data.getOptions().forEach(option -> {
			
			final Indicator typeCode = Indicator.valueOf(option.getOptionType().substring(0,1));
			double timeToExpiry = option.getExpiration().toEpochDay() - underlying.getLastTrade()
					.atZone(Constants.MARKET_TIME_ZONE).toLocalDate().toEpochDay();
			double riskFreeRate = 0.01;
			
			if(option.getBid() != null)
				option.setBidIV(BlackScholes.reverse(option.getBid(), typeCode, timeToExpiry, underlying.getPrice(), option.getStrikePrice(), riskFreeRate));
			
			if(option.getAsk() != null)
				option.setAskIV(BlackScholes.reverse(option.getAsk(), typeCode, timeToExpiry, underlying.getPrice(), option.getStrikePrice(), riskFreeRate));
			
			results.add(option);
		});
		dataUploadService.saveAll(symbol, results);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println(runningTime + " millisecs (" + (runningTime / 1000.0) + ")secs");
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}
}

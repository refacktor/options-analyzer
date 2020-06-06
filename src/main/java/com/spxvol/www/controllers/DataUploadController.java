package com.spxvol.www.controllers;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
import com.fasterxml.uuid.Generators;
import com.spxvol.math.BlackScholes;
import com.spxvol.math.BlackScholes.Indicator;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.model.Constants;
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

	@PostMapping("/putData")
	public ResponseEntity<String> uploadDataFromEtradeAPI(@RequestBody String json) throws Exception {
		final JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		List<OptionQuote> results = new ArrayList<>();
		rootNode.withArray("options").elements().forEachRemaining(optionsNodes -> {
			JsonNode optionChainResponseNode = optionsNodes.get("OptionChainResponse");
		
			JsonNode expirationNode = optionsNodes.get("Expiration");
			LocalDate expiration = LocalDate.of(expirationNode.get("year").asInt(),
					expirationNode.get("month").asInt(), expirationNode.get("day").asInt());
		
			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				long uniquePair = Generators.timeBasedGenerator().generate().timestamp();
				JsonNode[] optionPairNodes = { optionPairNode.get("Call"), optionPairNode.get("Put") };
				for(JsonNode optionNode: optionPairNodes) {
					final String symbol1 = optionNode.get("symbol").asText();
					Underlying underlying = dataUploadService.underlying(symbol1);
					
					JsonNode optionGreeksNode = optionNode.get("OptionGreeks");
					Function<String, BigDecimal> greek = field -> {
						final double asDouble = optionGreeksNode.get(field).asDouble();
						return asDouble < 0.0 || asDouble > 1.0 ? null : BigDecimal.valueOf(asDouble);
					};
					final String optionType = optionNode.get("optionType").asText();
					final double bid = optionNode.get("bid").asDouble();
					final double ask = optionNode.get("ask").asDouble();
					final double strike = optionNode.get("strikePrice").asDouble();
					final OptionQuote build = new OptionQuote(uniquePair, optionNode.get("optionCategory").asText(),
					optionNode.get("optionRootSymbol").asText(), expiration, optionNode.get("adjustedFlag").asBoolean(),
					optionNode.get("displaySymbol").asText(), optionType,
					BigDecimal.valueOf(strike), underlying,
					BigDecimal.valueOf(bid), BigDecimal.valueOf(ask),
					optionNode.get("bidSize").asLong(), optionNode.get("askSize").asLong(), optionNode.get("inTheMoney").asText(),
					optionNode.get("volume").asLong(), optionNode.get("openInterest").asInt(),
					BigDecimal.valueOf(optionNode.get("netChange").asDouble()),
					BigDecimal.valueOf(optionNode.get("lastPrice").asDouble()), optionNode.get("quoteDetail").asText(),
					optionNode.get("osiKey").asText(), greek.apply("rho"), greek.apply("vega"), greek.apply("theta"),
					greek.apply("delta"), greek.apply("gamma"), greek.apply("iv"),
					optionGreeksNode.get("currentValue").asBoolean());
					
					final JsonNode all = rootNode.get("stock").get("All");
					final double stockPrice = all.get("lastTrade").asDouble();
					int timeOfLastTrade = all.get("timeOfLastTrade").asInt();
					final Indicator typeCode = Indicator.valueOf(optionType.substring(0,1));
					double timeToExpiry = expiration.toEpochDay() - Instant.ofEpochSecond(timeOfLastTrade)
							.atZone(Constants.MARKET_TIME_ZONE).toLocalDate().toEpochDay();
					double riskFreeRate = 0.01;
					
					build.setBidIV(BlackScholes.reverse(bid, typeCode, timeToExpiry, stockPrice, strike, riskFreeRate));
					build.setAskIV(BlackScholes.reverse(ask, typeCode, timeToExpiry, stockPrice, strike, riskFreeRate));
					
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
}

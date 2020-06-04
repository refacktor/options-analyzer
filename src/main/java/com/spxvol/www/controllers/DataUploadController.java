package com.spxvol.www.controllers;

import java.math.BigDecimal;
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
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.Underlying;
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
		rootNode.withArray("options").elements().forEachRemaining(node -> {
			JsonNode optionChainResponseNode = node.get("OptionChainResponse");
		
			JsonNode expirationNode = node.get("Expiration");
			LocalDate expiration = LocalDate.of(expirationNode.get("year").asInt(),
					expirationNode.get("month").asInt(), expirationNode.get("day").asInt());
		
			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				long uniquePair = Generators.timeBasedGenerator().generate().timestamp();
				JsonNode[] nodes = { optionPairNode.get("Call"), optionPairNode.get("Put") };
				for(JsonNode j: nodes) {
					final String symbol1 = j.get("symbol").asText();
					Underlying underlying = dataUploadService.underlying(symbol1);
					
					JsonNode optionGreeksNode = j.get("OptionGreeks");
					Function<String, BigDecimal> greek = field -> {
						final double asDouble = optionGreeksNode.get(field).asDouble();
						return asDouble < 0.0 || asDouble > 1.0 ? null : BigDecimal.valueOf(asDouble);
					};
					final OptionQuote build = new OptionQuote(uniquePair, j.get("optionCategory").asText(),
					j.get("optionRootSymbol").asText(), expiration, j.get("adjustedFlag").asBoolean(),
					j.get("displaySymbol").asText(), j.get("optionType").asText(),
					BigDecimal.valueOf(j.get("strikePrice").asDouble()), underlying,
					BigDecimal.valueOf(j.get("bid").asDouble()), BigDecimal.valueOf(j.get("ask").asDouble()),
					j.get("bidSize").asLong(), j.get("askSize").asLong(), j.get("inTheMoney").asText(),
					j.get("volume").asLong(), j.get("openInterest").asInt(),
					BigDecimal.valueOf(j.get("netChange").asDouble()),
					BigDecimal.valueOf(j.get("lastPrice").asDouble()), j.get("quoteDetail").asText(),
					j.get("osiKey").asText(), greek.apply("rho"), greek.apply("vega"), greek.apply("theta"),
					greek.apply("delta"), greek.apply("gamma"), greek.apply("iv"),
					optionGreeksNode.get("currentValue").asBoolean());
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

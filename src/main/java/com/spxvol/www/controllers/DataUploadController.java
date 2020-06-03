package com.spxvol.www.controllers;

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
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class DataUploadController {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private final OptionQuoteService optionQuoteService;

	private final ObjectMapper mapper = JsonMapper.builder().addModule(new ParameterNamesModule()).addModule(new Jdk8Module())
			.addModule(new JavaTimeModule()).build();
	
	public DataUploadController(OptionQuoteService optionQuoteService) {
		super();
		this.optionQuoteService = optionQuoteService;
	}

	@PostMapping("/putData")
	public ResponseEntity<String> putData(@RequestBody String json) throws Exception {
		final JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		final List<OptionQuote> quotes = optionQuoteService.build(rootNode);
		optionQuoteService.saveAll(symbol, quotes);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println(runningTime + " millisecs (" + (runningTime / 1000.0) + ")secs");
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}
}

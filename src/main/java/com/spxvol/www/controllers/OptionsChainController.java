package com.spxvol.www.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.model.SymbolSearch;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class OptionsChainController {

	private final ObjectMapper mapper;
	
	private final OptionQuoteService optionQuoteService;
	
	public OptionsChainController(OptionQuoteService optionQuoteService) {
		this.optionQuoteService = optionQuoteService;
		mapper = JsonMapper.builder().addModule(new ParameterNamesModule()).addModule(new Jdk8Module())
				.addModule(new JavaTimeModule()).build();

	}

	@GetMapping("/getOptionChains")
	public ModelAndView getOptionChains(@ModelAttribute SymbolSearch symbolSearch, ModelMap model) {

		model.addAttribute("symbol", symbolSearch.getSymbol());
		model.addAttribute("chainMap", optionQuoteService.chainMap(symbolSearch.getSymbol()));
		return new ModelAndView("chain", model);
	}

	@PostMapping("/putData")
	public ResponseEntity<String> putData(@RequestBody String json) throws Exception {
		final JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		final List<OptionQuote> quotes = optionQuoteService.build(rootNode);
		optionQuoteService.saveAll(quotes);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println(runningTime + " millisecs (" + (runningTime / 1000.0) + ")secs");
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}

	@GetMapping("/chain")
	public String chain(Model model) throws Exception {
		model.addAttribute("symbolSearch", new SymbolSearch());
		return "chain";
	}

	@GetMapping("/")
	public String home(Model model) throws Exception {
		return "index";
	}
	
	@GetMapping("/privacy.html")
	public String privacy() {
		return "privacy";
	}

	@GetMapping("/symbols")
	public String symbols(Model model) throws Exception {
		model.addAttribute("symbolList", optionQuoteService.allSymbols());
		return "symbols";
	}
}

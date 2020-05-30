package com.options.analyzer.optionsanalyzer.controllers;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.options.analyzer.optionsanalyzer.model.SymbolSearch;
import com.options.analyzer.optionsanalyzer.model.entity.OptionQuote;
import com.options.analyzer.optionsanalyzer.repo.OptionQuoteRepository;
import com.options.analyzer.optionsanalyzer.utils.Utils;

@Controller
public class OptionsChainController {

	private final OptionQuoteRepository OptionQuoteRepository;
	private final ObjectMapper mapper;
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Autowired
	public OptionsChainController(OptionQuoteRepository OptionQuoteRepository) {
		this.OptionQuoteRepository = OptionQuoteRepository;
		mapper = JsonMapper.builder().addModule(new ParameterNamesModule()).addModule(new Jdk8Module())
				.addModule(new JavaTimeModule()).build();

	}

	@GetMapping("/getOptionChains")
	public ModelAndView getOptionChains(@ModelAttribute SymbolSearch symbolSearch, ModelMap model) {
		List<OptionQuote> chains = OptionQuoteRepository.findBySymbol(symbolSearch.getSymbol());
		Comparator<OptionQuote> strikePriceComparator = (o1, o2) -> o1.getStrikePrice().compareTo(o2.getStrikePrice());
		Comparator<OptionQuote> dateTimeComparator = (o1, o2) -> o1.getTimeStamp().compareTo(o2.getTimeStamp());
		Map<LocalDate, Map<Long, Map<String, List<OptionQuote>>>> chainMap = chains.stream()
				.sorted(strikePriceComparator).sorted(dateTimeComparator)
				.collect(groupingBy(OptionQuote::getDate, LinkedHashMap::new, groupingBy(OptionQuote::getUniquePair,
						LinkedHashMap::new, groupingBy(OptionQuote::getOptionType))));

		model.addAttribute("symbol", symbolSearch.getSymbol());
		model.addAttribute("totalChain", chains.size());
		model.addAttribute("chainMap", chainMap);
		return new ModelAndView("index", model);
	}

	@PostMapping("/putData")
	public ResponseEntity<String> putData(@RequestBody String json) throws Exception {
		final JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		System.out.println(" start uploading data for symbol " + symbol);
		long startTime = System.currentTimeMillis();
		Utils.getOptionPairs(rootNode, batchSize).parallelStream().forEach(OptionQuoteRepository::saveAll);
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		System.out.println(runningTime + " millisecs (" + (runningTime / 1000.0) + ")secs");
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}

	@GetMapping("/")
	public String home(Model model) throws Exception {
		model.addAttribute("symbolSearch", new SymbolSearch());
		return "index";
	}
}

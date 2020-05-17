package com.options.analyzer.optionsanalyzer.controllers;

import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fasterxml.uuid.Generators;
import com.options.analyzer.optionsanalyzer.model.SymbolSearch;
import com.options.analyzer.optionsanalyzer.model.entity.OptionPair;
import com.options.analyzer.optionsanalyzer.repo.OptionPairRepository;

@Controller
public class OptionsChainController {

	private final OptionPairRepository optionPairRepository;
	private final ObjectMapper mapper;

	@Autowired
	public OptionsChainController(OptionPairRepository optionPairRepository) {
		this.optionPairRepository = optionPairRepository;
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule()).addModule(new Jdk8Module()).addModule(new JavaTimeModule())
				.build();

	}

	@GetMapping("/getOptionChains")
	public ModelAndView getOptionChains(@ModelAttribute SymbolSearch symbolSearch, ModelMap model) {
		List<OptionPair> chains = optionPairRepository.findBySymbol(symbolSearch.getSymbol());
		
		Comparator<OptionPair> strikePriceComparator = (o1, o2) -> o1.getStrikePrice().compareTo(o2.getStrikePrice());
		
		Comparator<OptionPair> dateTimeComparator = (o1, o2) -> o1.getTimeStamp().compareTo(o2.getTimeStamp());
		
		Map<LocalDate, Map<Long,Map<String, List<OptionPair>>>> chainMap = 
				 chains
				.stream()
				.sorted(strikePriceComparator)
				.sorted(dateTimeComparator)
				.collect(groupingBy(OptionPair::getDate, LinkedHashMap::new,groupingBy(OptionPair::getUniquePair,LinkedHashMap::new,groupingBy(OptionPair::getOptionType))));
		
		
		model.addAttribute("symbol", symbolSearch.getSymbol());
		model.addAttribute("totalChain", chains.size());
		model.addAttribute("chainMap", chainMap);
		return new ModelAndView("index", model);
	}

	
	
	
	@PostMapping("/putData")
	public ResponseEntity<String> putData(@RequestBody String json) throws Exception {
		JsonNode rootNode = mapper.readTree(json);
		String symbol = rootNode.get("stock").get("Product").get("symbol").asText();
		System.out.println(" start uploading data for symbol " +symbol );
		processOptionPair(rootNode);
		return new ResponseEntity<String>("data uploaded successfully for symbol " + symbol, HttpStatus.OK);
	}

	@GetMapping("/")
	public String home(Model model) throws Exception {
		model.addAttribute("symbolSearch", new SymbolSearch());
		return "index";
	}

	private void processOptionPair(JsonNode rootNode) {
		rootNode.withArray("options").elements().forEachRemaining(node -> {
			JsonNode optionChainResponseNode = node.get("OptionChainResponse");
			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				long uniquePair = Generators.timeBasedGenerator().generate().timestamp();
				JsonNode callNode = optionPairNode.get("Call");
				JsonNode putNode = optionPairNode.get("Put");
				optionPairRepository.save(getOptionPair(uniquePair, callNode));
				optionPairRepository.save(getOptionPair(uniquePair, putNode));
			});
		});
	}

	private OptionPair getOptionPair(long uniquePair, JsonNode jsonNode) {
		JsonNode optionGreeksNode = jsonNode.get("OptionGreeks");
		return new OptionPair(uniquePair, jsonNode.get("optionCategory").asText(),
				jsonNode.get("optionRootSymbol").asText(),
				getLocalDateTime(jsonNode.get("quoteDetail").asText(), jsonNode.get("symbol").asText(),jsonNode.get("optionType").asText()),
				jsonNode.get("adjustedFlag").asBoolean(), jsonNode.get("displaySymbol").asText(),
				jsonNode.get("optionType").asText(), BigDecimal.valueOf(jsonNode.get("strikePrice").asDouble()),
				jsonNode.get("symbol").asText(), BigDecimal.valueOf(jsonNode.get("bid").asDouble()),
				BigDecimal.valueOf(jsonNode.get("ask").asDouble()), jsonNode.get("bidSize").asLong(),
				jsonNode.get("askSize").asLong(), jsonNode.get("inTheMoney").asText(), jsonNode.get("volume").asLong(),
				BigDecimal.valueOf(jsonNode.get("openInterest").asDouble()),
				BigDecimal.valueOf(jsonNode.get("netChange").asDouble()),
				BigDecimal.valueOf(jsonNode.get("lastPrice").asDouble()), jsonNode.get("quoteDetail").asText(),
				jsonNode.get("osiKey").asText(), BigDecimal.valueOf(optionGreeksNode.get("rho").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("vega").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("theta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("delta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("gamma").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("iv").asDouble()),
				optionGreeksNode.get("currentValue").asBoolean());
	}
	
	private LocalDateTime getLocalDateTime(String quoteDetail, String symbol, String optionType) {	
		String date = quoteDetail.substring(quoteDetail.lastIndexOf("/")+1);
		date = date.substring(0,date.lastIndexOf(":"));
		date = date.substring(symbol.length() + 1,date.length() - optionType.length() - 1);
		String year = date.substring(0,4);
		String monthSpliter = date.substring(5);
		String month = monthSpliter.split(":")[0];
		String day = monthSpliter.split(":")[1];
		day = day.length() < 2?"0"+day:day;
		month = month.length() < 2?"0"+month:month;
		return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)).atStartOfDay(ZoneOffset.UTC).toLocalDateTime();
	}
}

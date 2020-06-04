package com.spxvol.www.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.spxvol.www.model.SymbolSearch;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class OptionsChainController {

	private final OptionQuoteService optionQuoteService;

	public OptionsChainController(OptionQuoteService optionQuoteService) {
		this.optionQuoteService = optionQuoteService;
	}

	@GetMapping("/getOptionChains")
	public ModelAndView getOptionChains(@ModelAttribute SymbolSearch symbolSearch, ModelMap model) {

		model.addAttribute("symbol", symbolSearch.getSymbol());
		model.addAttribute("chainMap", optionQuoteService.chainMap(symbolSearch.getSymbol()));
		return new ModelAndView("chain", model);
	}

	@GetMapping("/chain")
	public String chain(Model model) throws Exception {
		model.addAttribute("symbolSearch", new SymbolSearch());
		return "chain";
	}

	@GetMapping("/symbols")
	public String symbols(Model model) throws Exception {
		model.addAttribute("aggregationSummary", optionQuoteService.aggregation());
		return "symbols";
	}
}

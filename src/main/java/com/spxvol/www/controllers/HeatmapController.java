package com.spxvol.www.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spxvol.www.model.SymbolSearch;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class HeatmapController {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private final OptionQuoteService optionQuoteService;
	
	public HeatmapController(OptionQuoteService optionQuoteService) {
		super();
		this.optionQuoteService = optionQuoteService;
	}

	@RequestMapping("/heatmap")
	public String heatmap(Model model, @ModelAttribute SymbolSearch symbol) {
		if(symbol.getSymbol() == null) {
			symbol.setSymbol("SPY");
		}
		if(symbol.getSkipStrikes() == null) {
			symbol.setSkipStrikes(Boolean.TRUE);
		}
		model.addAttribute("heatmap", optionQuoteService.heatmap(symbol.getSymbol(), symbol.getSkipStrikes()));
		return "heatmap";
	}
}

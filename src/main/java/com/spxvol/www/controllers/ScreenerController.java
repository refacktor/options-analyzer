package com.spxvol.www.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spxvol.www.model.ScreenerParams;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class ScreenerController {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private final OptionQuoteService optionQuoteService;

	public ScreenerController(OptionQuoteService optionQuoteService) {
		super();
		this.optionQuoteService = optionQuoteService;
	}

	@GetMapping("/screener")
	public String screener(Model model, @ModelAttribute("screenerParams") ScreenerParams params) {
		if (params.getSymbols() == null) {
			params.setSymbols("SPX");
		}
		if (params.getMinProbability() == null) {
			params.setMinProbability((float) 0.333);
		}
		if (params.getMaxProbability() == null) {
			params.setMaxProbability((float) 0.666);
		}
		if (params.getMinDays() == null) {
			params.setMinDays(0);
		}
		if (params.getMaxDays() == null) {
			params.setMaxDays(90);
		}
		model.addAttribute("optionList", optionQuoteService.search(params));
		return "screener";
	}
}

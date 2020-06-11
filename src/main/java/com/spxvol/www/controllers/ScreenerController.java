package com.spxvol.www.controllers;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spxvol.www.datastore.UnderlyingRepository;
import com.spxvol.www.model.ScreenerParams;
import com.spxvol.www.services.OptionQuoteService;

@Controller
public class ScreenerController {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private final OptionQuoteService optionQuoteService;
	
	private final UnderlyingRepository underlyingRepository;

	public ScreenerController(OptionQuoteService optionQuoteService, UnderlyingRepository underlyingRepository) {
		super();
		this.optionQuoteService = optionQuoteService;
		this.underlyingRepository = underlyingRepository;
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
		model.addAttribute("underlyings", 
				Arrays.stream(params.getSymbols().toUpperCase().split("\\W+"))
				.map(underlyingRepository::findById)
				.map(Optional::get)
				.collect(Collectors.toList()));
		model.addAttribute("optionList", optionQuoteService.search(params));
		return "screener";
	}
}

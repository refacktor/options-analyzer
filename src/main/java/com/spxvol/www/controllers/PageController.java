package com.spxvol.www.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/")
	public String home(Model model) throws Exception {
		return "index";
	}

	@GetMapping("/privacy.html")
	public String privacy() {
		return "privacy";
	}

	@GetMapping("/terms.html")
	public String terms() {
		return "terms";
	}
}

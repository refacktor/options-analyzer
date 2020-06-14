package com.spxvol.www.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spxvol.www.datasources.OptionsDataSource;
import com.spxvol.www.model.StandardQuote;

@Controller
public class DataRefreshController {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private final OptionsDataSource source;

	private final DataUploadController uploader;

	public DataRefreshController(OptionsDataSource source, DataUploadController uploader) {
		super();
		this.source = source;
		this.uploader = uploader;
	}

	@GetMapping("/internal/yahoo-refresh/{stock}")
	@ResponseBody
	public String refresh(@PathVariable("stock") String stock) throws Exception {
		StandardQuote quote = source.getQuote(stock);
		uploader.upload(quote);
		return "ok";
	}

}

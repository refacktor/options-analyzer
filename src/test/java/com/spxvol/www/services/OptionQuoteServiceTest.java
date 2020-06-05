package com.spxvol.www.services;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spxvol.www.controllers.DataUploadController;
import com.spxvol.www.datastore.AggregationSummary;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.model.ScreenerParams;

@SpringBootTest
class OptionQuoteServiceTest {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired private DataUploadController uploader;

	@Autowired private OptionQuoteService target;

	@BeforeAll
	public void loadData() throws Exception {
		String json = Files.contentOf(new File("src/test/resources/etrade/SPX.json"), "utf-8");
		uploader.uploadDataFromEtradeAPI(json);
	}
	
	@Test
	public void testSearch() {
		ScreenerParams sp = new ScreenerParams();
		sp.setSymbols("SPX");
		List<OptionQuote> search = target.search(sp);
		
		Assertions.assertTrue(search.size() > 0);
	}

	@Test
	public void testSummarize() {
		List<AggregationSummary> summarize = target.summarize();
		
		logger.info(summarize.toString());
	}
}

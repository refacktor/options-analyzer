package com.spxvol.www.datasources;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spxvol.jsonschema2pojo.yahoo.YahooSchema;
import com.spxvol.www.controllers.DataUploadController;
import com.spxvol.www.model.StandardQuote;

@SpringBootTest
class YahooDataSourceTest {

	private final Logger logger = Logger.getLogger(getClass().getName());

	private ObjectMapper mapper = new ObjectMapper();
	{
		mapper.registerModule(new JavaTimeModule());
	}
	
	@Autowired
	private YahooDataSource target;
	
	@Autowired
	private DataUploadController uploader;
	
	@Test
	void testConversion() throws JsonParseException, JsonMappingException, IOException {
		YahooSchema ys = mapper.readValue(new File("src/main/resources/schemas/yahoo.sample.json"), 
				YahooSchema.class);
		StandardQuote quote = target.convert("SPX", ys);
		Assertions.assertEquals("^SPX", ys.getOptionChain().getResult().get(0).getQuote().getSymbol());
		Assertions.assertEquals("SPX", quote.getUnderlying().getSymbol()); 
	}
	
	@Test
	void testImport() throws Exception {
		File file = new File("src/test/resources/yahoo/SPX-converted.json");
		StandardQuote quote = mapper.readValue(file, StandardQuote.class);
		ResponseEntity<String> out = uploader.upload(quote);
		logger.info(out.toString());
	}
}

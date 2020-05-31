package com.spxvol.datasources.iexcloud;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spxvol.datasources.iexcloud.ApiClient;
import com.spxvol.datasources.iexcloud.OptionsChain;

@SpringBootTest(classes = ApiClient.class)
class ApiClientTest {

	@Autowired private ApiClient target;
	
	private final Logger logger = Logger.getLogger(getClass().getName());

	@Test
	public void test() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<OptionsChain> options = target.getOptionsChain("GLD");
		logger.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(options));
	}
}

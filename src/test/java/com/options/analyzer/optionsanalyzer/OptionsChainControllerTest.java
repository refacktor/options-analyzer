package com.options.analyzer.optionsanalyzer;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.options.analyzer.optionsanalyzer.controllers.OptionsChainController;

@SpringBootTest
class OptionsChainControllerTest {

	@Autowired private OptionsChainController target;
	
	@Test
	void loadSPY() throws Exception {
		final String pathname = "src/main/resources/json/SPY.json";
		loadData(pathname);
	}

	@Test
	void loadTLT() throws Exception {
		final String pathname = "src/main/resources/json/TLT.json";
		loadData(pathname);
		
	}

	@Test
	void loadGLD() throws Exception {
		final String pathname = "src/main/resources/json/GLD.json";
		loadData(pathname);
	}

	@Test
	void loadSPX() throws Exception {
		final String pathname = "src/main/resources/json/SPX.json";
		loadData(pathname);
	}

	private void loadData(final String pathname) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = Files.contentOf(new File(pathname), "utf-8");
		ResponseEntity<String> out = target.putData(json);
		System.out.println(pathname + ": " + out);
	}

}

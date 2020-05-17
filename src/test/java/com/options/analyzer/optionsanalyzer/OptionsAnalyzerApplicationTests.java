package com.options.analyzer.optionsanalyzer;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class OptionsAnalyzerApplicationTests {

	@Test
	void loadSPY() {
		final String pathname = "src/main/resources/json/SPY.json";
		loadData(pathname);
		
	}

	@Test
	void loadTLT() {
		final String pathname = "src/main/resources/json/TLT.json";
		loadData(pathname);
		
	}

	@Test
	void loadGLD() {
		final String pathname = "src/main/resources/json/GLD.json";
		loadData(pathname);
	}

	@Test
	void loadTSLA() {
		final String pathname = "src/main/resources/json/TSLA.json";
		loadData(pathname);
	}

	private void loadData(final String pathname) {
		RestTemplate rt = new RestTemplate();
		
		String json = Files.contentOf(new File(pathname), "utf-8");
		
		HttpEntity<String> request = new HttpEntity<String>(json);
		
		String out = rt.postForObject("http://localhost:9090/putData", request, String.class);
		
		System.out.println(pathname + ": " + out);
	}

}

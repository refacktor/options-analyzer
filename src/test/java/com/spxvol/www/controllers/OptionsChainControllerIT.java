package com.spxvol.www.controllers;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

class OptionsChainControllerIT {

	@Test
	void loadSPY() throws Exception {
		final String pathname = "src/test/resources/json/SPY.json";
		loadData(pathname);
	}

	@Test
	void loadTLT() throws Exception {
		final String pathname = "src/test/resources/json/TLT.json";
		loadData(pathname);

	}

	@Test
	void loadGLD() throws Exception {
		final String pathname = "src/test/resources/json/GLD.json";
		loadData(pathname);
	}

	@Test
	void loadSPX() throws Exception {
		final String pathname = "src/test/resources/json/SPX.json";
		loadData(pathname);
	}

	private void loadData(final String pathname) throws Exception {
		RestTemplate rt = new RestTemplate();
		String json = Files.contentOf(new File(pathname), "utf-8");
		HttpEntity<String> request = new HttpEntity<String>(json);
		String out = rt.postForObject("http://localhost:9090/putData", request, String.class);
		System.out.println(pathname + ": " + out);
	}

}

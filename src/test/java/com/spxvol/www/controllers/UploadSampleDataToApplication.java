package com.spxvol.www.controllers;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

class UploadSampleDataToApplication {

	private static final String HTTP_LOCALHOST_9090_PUT_DATA = "http://localhost:9090/putData";

	@Test
	void loadSPY() throws Exception {
		final String pathname = "src/test/resources/etrade/SPY.json";
		loadData(pathname);
	}

	@Test
	void loadTLT() throws Exception {
		final String pathname = "src/test/resources/etrade/TLT.json";
		loadData(pathname);

	}

	@Test
	void loadGLD() throws Exception {
		final String pathname = "src/test/resources/etrade/GLD.json";
		loadData(pathname);
	}

	@Test
	void loadSPX() throws Exception {
		final String pathname = "src/test/resources/etrade/SPX.json";
		loadData(pathname);
	}

	private void loadData(final String pathname) throws Exception {
		RestTemplate rt = new RestTemplate();
		String json = Files.contentOf(new File(pathname), "utf-8");
		HttpEntity<String> request = new HttpEntity<String>(json);
		System.out.println("Uploading to " + HTTP_LOCALHOST_9090_PUT_DATA);
		String out = rt.postForObject(HTTP_LOCALHOST_9090_PUT_DATA, request, String.class);
		System.out.println(pathname + ": " + out);
	}

}

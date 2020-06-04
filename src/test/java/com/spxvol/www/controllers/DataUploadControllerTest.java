package com.spxvol.www.controllers;

import java.io.File;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DataUploadControllerTest {

	@Autowired private DataUploadController target;
	
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
		String json = Files.contentOf(new File(pathname), "utf-8");
		ResponseEntity<String> out = target.uploadDataFromEtradeAPI(json);
		System.out.println(pathname + ": " + out);
	}

}

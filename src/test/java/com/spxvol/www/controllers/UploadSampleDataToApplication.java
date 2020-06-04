package com.spxvol.www.controllers;

import java.io.File;

import org.assertj.core.util.Files;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

class UploadSampleDataToApplication {

	private static final String HTTP_LOCALHOST_9090_PUT_DATA = "http://localhost:9090/putData";

	public static void main(String[] args) throws Exception {
		File dir = new File("src/test/resources/etrade");
		for(File file: dir.listFiles()) {
			loadData(file);
		}
	}

	private static void loadData(File file) throws Exception {
		RestTemplate rt = new RestTemplate();
		String json = Files.contentOf(file, "utf-8");
		HttpEntity<String> request = new HttpEntity<String>(json);
		System.out.println("Uploading to " + HTTP_LOCALHOST_9090_PUT_DATA);
		String out = rt.postForObject(HTTP_LOCALHOST_9090_PUT_DATA, request, String.class);
		System.out.println(file.toString() + ": " + out);
	}

}

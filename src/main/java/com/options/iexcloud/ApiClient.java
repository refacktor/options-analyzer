package com.options.iexcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Fetches data from the iexcloud.io api
 */
@Service
public class ApiClient {

	private static final String SYMBOLS_END_POINT_PATH = "ref-data/symbols";
	private static final String OPTIONS_END_POINT_PATH = "stock/%s/options";
	private static final String OPTIONS_CHAIN_END_POINT_PATH = "stock/%s/options/%s";

	private static final String TOKEN_QUERY = "?token=";

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${api-base-url}")
	private String apiBaseUrl;

	@Value("${IEXCLOUD_PK}")
	private String apiToken;

	private String getUrl(String endPointPath) {
		return apiBaseUrl + endPointPath + TOKEN_QUERY + apiToken;
	}

	public Symbols[] getSymbols() {
		return restTemplate.getForObject(getUrl(SYMBOLS_END_POINT_PATH), Symbols[].class);
	}

	public List<Options> getOptionsDates(String symbol) {
		try {
			ResponseEntity<Options[]> optionsResponse = restTemplate
					.getForEntity(getUrl(String.format(OPTIONS_END_POINT_PATH, symbol)), Options[].class);
			if (optionsResponse.getStatusCode().is2xxSuccessful()) {
				return Arrays.asList(optionsResponse.getBody());
			}
		} catch (HttpStatusCodeException ex) {
			ex.printStackTrace();
			System.out.println(String.format("options for symbol %s not found ", symbol));
		}
		return new ArrayList<>();

	}

	public List<OptionsChain> getOptionsChain(String symbol) {
		return getOptionsChain(symbol, getOptionsDates(symbol));
	}

	public List<OptionsChain> getOptionsChain(String symbol, List<Options> options) {
		List<OptionsChain> optionsChainResults = new ArrayList<OptionsChain>();
		options.forEach(option -> {
			try {
				String url = getUrl(String.format(OPTIONS_CHAIN_END_POINT_PATH, symbol, option.getExpirationDate()));
				System.out.println(url);
				ResponseEntity<OptionsChain[]> optionsChainResponse = restTemplate.getForEntity(url,
						OptionsChain[].class);

				if (optionsChainResponse.getStatusCode().is2xxSuccessful()) {
					optionsChainResults.addAll(Arrays.asList(optionsChainResponse.getBody()));
				}
			} catch (HttpStatusCodeException ex) {
				ex.printStackTrace();
				System.out.println(String.format("options-chain for symbol %s and expirationDate %s not found ", symbol,
						option.getExpirationDate()));
			}
		});
		return optionsChainResults;
	}
}

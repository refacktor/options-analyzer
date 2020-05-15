package com.options.analyzer.optionsanalyzer.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableMap;
import com.options.analyzer.optionsanalyzer.model.Options;
import com.options.analyzer.optionsanalyzer.model.Symbols;
import com.options.analyzer.optionsanalyzer.model.entity.OptionsChain;
import com.options.analyzer.optionsanalyzer.repo.OptionsChainRepository;

/**
 * Responsible to keep fetching data from the api
 */
@Service
public class ApiService {

	private static final String SYMBOLS_END_POINT_PATH = "ref-data/symbols";
	private static final String OPTIONS_END_POINT_PATH = "stock/%s/options";
	private static final String OPTIONS_CHAIN_END_POINT_PATH = "stock/%s/options/%s";

	private static final String TOKEN_QUERY = "?token=";
	private final OptionsChainRepository optionsChainRepository;

	private final RestTemplate restTemplate;
	@Value("${api-base-url}")
	private String apiBaseUrl;
	@Value("${api-token}")
	private String apiToken;

	private AtomicBoolean running = new AtomicBoolean(false);

	@Autowired
	public ApiService(OptionsChainRepository optionsChainRepository, RestTemplate restTemplate) {
		this.optionsChainRepository = optionsChainRepository;
		this.restTemplate = restTemplate;

	}

	private String getUrl(String endPointPath) {
		return apiBaseUrl + endPointPath + TOKEN_QUERY + apiToken;
	}

	@Scheduled(cron = "${api.cron}")
	public void dataRetrieved() {
		try {
			if (!running.get()) {
				running.set(true);
				System.out.println("Fetch options chain data ...");
				List<Symbols> symbols = Arrays.asList(getSymbols());

//			List<OptionsChain> chains = symbols.parallelStream().peek(s -> System.out.println("processing symbol " + s))
//					.map(s -> getOptions(s.getSymbol())).peek(map -> System.out.println("options " + map))
//					.flatMap(map -> map.entrySet().stream()).map(e -> getOptionsChain(e.getKey(), e.getValue()))
//					.flatMap(List::stream).collect(Collectors.toList());

				List<CompletableFuture<List<OptionsChain>>> chains = symbols.parallelStream()
						.peek(s -> System.out.println("processing symbol " + s)).map(s -> getChains2(s.getSymbol()))
						.peek(map -> System.out.println("options " + map)).collect(Collectors.toList());

				CompletableFuture<Void> allFutures = CompletableFuture
						.allOf(chains.toArray(new CompletableFuture[chains.size()]));

				CompletableFuture<List<List<OptionsChain>>> allChainsFuture = allFutures.thenApply(v -> {
					return chains.stream().map(chainFuture -> chainFuture.join()).collect(Collectors.toList());
				});

				allChainsFuture.get().forEach(list -> saveChains(list));

				running.set(false);
				System.out.println("Finish fetching options chain data ...");
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	private CompletableFuture<List<OptionsChain>> getChains2(String symbol) {
		return CompletableFuture.supplyAsync(() -> {
			return getOptionsChain(symbol, getOptions3(symbol));
		});
	}

	@Async
	public CompletableFuture<List<OptionsChain>> saveChains(final List<OptionsChain> optionsChains) {
		List<OptionsChain> savedOptionsChains = optionsChainRepository.saveAll(optionsChains);
		return CompletableFuture.completedFuture(savedOptionsChains);
	}

	private Symbols[] getSymbols() {
		return restTemplate.getForObject(getUrl(SYMBOLS_END_POINT_PATH), Symbols[].class);
	}

	private List<Options> getOptions3(String symbol) {
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

	private Map<String, List<Options>> getOptions(String symbol) {
		try {
			ResponseEntity<Options[]> optionsResponse = restTemplate
					.getForEntity(getUrl(String.format(OPTIONS_END_POINT_PATH, symbol)), Options[].class);
			if (optionsResponse.getStatusCode().is2xxSuccessful()) {
				return ImmutableMap.of(symbol, Arrays.asList(optionsResponse.getBody()));
			}
		} catch (HttpStatusCodeException ex) {
			ex.printStackTrace();
			System.out.println(String.format("options for symbol %s not found ", symbol));
		}
		return new HashMap<>();
	}

	private CompletableFuture<List<OptionsChain>> getOptionsChain2(String symbol, List<Options> options) {
		return CompletableFuture.supplyAsync(() -> {
			return getOptionsChain(symbol, options);
		});
	}

	private List<OptionsChain> getOptionsChain(String symbol, List<Options> options) {
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

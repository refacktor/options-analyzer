package com.options.analyzer.optionsanalyzer.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.io.Resources;
import com.options.analyzer.optionsanalyzer.model.Options;
import com.options.analyzer.optionsanalyzer.model.entity.OptionsChain;
import com.options.analyzer.optionsanalyzer.repo.OptionsChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Responsible to keep fetching data from the api
 */
@Service
public class ApiService {

    private static final String SYMBOLS_END_POINT_PATH = "ref-data/symbols";
    private static final String OPTIONS_END_POINT_PATH = "stock/%s/options";
    private static final String OPTIONS_CHAIN_END_POINT_PATH = "/stock/%s/options/%s";

    private static final String TOKEN_QUERY = "?token=";
    private final OptionsChainRepository optionsChainRepository;

    private final RestTemplate restTemplate;
    @Value("${api-base-url}")
    private String apiBaseUrl;
    @Value("${api-token}")
    private String apiToken;


    @Autowired
    public ApiService(OptionsChainRepository optionsChainRepository, RestTemplate restTemplate) {
        this.optionsChainRepository = optionsChainRepository;
        this.restTemplate = restTemplate;

    }

    private String getUrl(String endPointPath) {
        return apiBaseUrl + endPointPath + TOKEN_QUERY + apiToken;
    }

    @Scheduled(cron = "${api.cron}")
    @Transactional
    public void dataRetrieved() {
        System.out.println("Fetch options chain data ...");
        String symbolUrl = getUrl(SYMBOLS_END_POINT_PATH);
        //@TODO should remove these comments lines and read the data from the api
        //@TODO  for now we are reading the json from the local file system until we have a stable api

        //Arrays.asList(restTemplate.getForObject(symbolUrl, Symbols[].class));
//        symbols.stream().forEach(symbol -> {
//                    List<Options> options = getOptions(symbol.getSymbolId());
//                    options.p
//                            forEach(option -> {
//                                        List<OptionsChain> optionsChains = getOptionsChain(symbol.getSymbolId(), option.getExpirationDate());
//                                        optionsChainRepository.saveAll(optionsChains);
//                                    }
//                            );
//                }
//        );

        List<OptionsChain> optionsChainsSpy = getOptionsChain("SPY", "20201120");
        optionsChainRepository.saveAll(optionsChainsSpy);
        List<OptionsChain> optionsChainsAA = getOptionsChain("AA", "20200520");
        optionsChainRepository.saveAll(optionsChainsAA);
        System.out.println("Finish fetching options chain data ...");
    }

    private List<Options> getOptions(String symbol) {
        try {
            //ResponseEntity<Options[]> optionsResponse = restTemplate.getForEntity(getUrl(String.format(OPTIONS_END_POINT_PATH, symbol)), Options[].class);
            //if (optionsResponse.getStatusCode().is2xxSuccessful()) {
            //List<Options> options = Arrays.asList(optionsResponse.getBody());
            //return options;
            return Arrays.asList(new Options("202011"), new Options("202010"), new Options("202005"));
            //}
        } catch (HttpStatusCodeException ex) {
            System.out.println(String.format("options for symbol %s not found ", symbol));
        }
        return new ArrayList<>();
    }

    private List<OptionsChain> getOptionsChain(String symbol, String expirationDate) {
        try {
            //ResponseEntity<OptionsChain[]> optionsChainResponse = restTemplate.getForEntity(getUrl(String.format(OPTIONS_CHAIN_END_POINT_PATH, symbol, expirationDate)), OptionsChain[].class);
            //if (optionsChainResponse.getStatusCode().is2xxSuccessful()) {
            //  List<OptionsChain> optionsChains = Arrays.asList(optionsChainResponse.getBody());
            // return optionsChains;
            //}
            //{"symbol":"SPY","id":"SPY20201120C00215000","expirationDate":"20201120","contractSize":100,"strikePrice":215,"closingPrice":38.900002,
            // "side":"call","type":"equity","volume":0,"openInterest":17,"bid":77.739998,"ask":78.889999,"lastUpdated":"2020-05-07","isAdjusted":false}


            //File file = ResourceUtils.getFile("classpath:options-chain_"+symbol+".json");


            String file = Resources.toString(Resources.getResource(ApiService.class, "/json/options-chain_" + symbol + ".json"), StandardCharsets.UTF_8);
            //File file = new ClassPathResource("/json/options-chain_"+symbol+".json").getFile();
            ObjectMapper mapper = JsonMapper.builder() // or different mapper for other format
                    .addModule(new ParameterNamesModule())
                    .addModule(new Jdk8Module())
                    .addModule(new JavaTimeModule())
                    .build();

            return Arrays.asList(mapper.readValue(file, OptionsChain[].class));

        } catch (HttpStatusCodeException | IOException ex) {
            ex.printStackTrace();
            System.out.println(String.format("options-chain for symbol %s and expirationDate %s not found ", symbol, expirationDate));
        }
        return new ArrayList<>();
    }
}

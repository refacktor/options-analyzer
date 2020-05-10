package com.options.analyzer.optionsanalyzer.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Responsible to keep fetching data from the api
 */
@Service
public class ApiService {

    private final String apiBaseUrl;
    private final String apiToken;

    public ApiService(@Value("${api-base-url}") String apiBaseUrl, @Value("${api-token}") String apiToken){
        this.apiBaseUrl = apiBaseUrl;
        this.apiToken = apiToken;
    }


}

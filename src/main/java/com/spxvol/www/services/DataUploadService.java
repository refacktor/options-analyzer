package com.spxvol.www.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.OptionQuoteRepository;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.datastore.UnderlyingRepository;

@Component
public class DataUploadService {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private final OptionQuoteRepository optionQuoteRepository;
	
	private final UnderlyingRepository underlyingRepository;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") private int batchSize;

	public DataUploadService(OptionQuoteRepository optionQuoteRepository, UnderlyingRepository underlyingRepository) {
		super();
		this.optionQuoteRepository = optionQuoteRepository;
		this.underlyingRepository = underlyingRepository;
	}

	public void saveAll(String symbol, List<OptionQuote> quotes) {
		optionQuoteRepository.deleteAll(optionQuoteRepository.findBySymbol(symbol));
		Lists.partition(quotes, batchSize).parallelStream().forEach(optionQuoteRepository::saveAll);
	}

	public Underlying underlying(final String symbol) {
		return underlyingRepository.findById(symbol).orElseGet(() -> underlyingRepository.save(new Underlying(symbol)));
	}
}

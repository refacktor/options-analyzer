package com.options.analyzer.optionsanalyzer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.options.analyzer.optionsanalyzer.model.entity.OptionQuote;

public interface OptionQuoteRepository extends JpaRepository<OptionQuote, Long>{
	List<OptionQuote> findBySymbol(String symbol);

}

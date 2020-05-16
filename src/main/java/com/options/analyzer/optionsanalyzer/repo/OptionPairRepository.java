package com.options.analyzer.optionsanalyzer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.options.analyzer.optionsanalyzer.model.entity.OptionPair;

public interface OptionPairRepository extends JpaRepository<OptionPair, Long>{
	List<OptionPair> findBySymbol(String symbol);

}

package com.options.analyzer.optionsanalyzer.repo;

import com.options.analyzer.optionsanalyzer.model.entity.OptionsChain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsChainRepository extends JpaRepository<OptionsChain, String> {
    List<OptionsChain> findBySymbol(String symbol);
    List<OptionsChain> findBySymbolAndSide(String symbol, String side);
}

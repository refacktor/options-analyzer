package com.options.iexcloud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsChainRepository extends JpaRepository<OptionsChain, String> {
    List<OptionsChain> findBySymbol(String symbol);

}

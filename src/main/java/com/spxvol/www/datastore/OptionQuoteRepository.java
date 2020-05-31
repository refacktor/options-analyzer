package com.spxvol.www.datastore;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionQuoteRepository extends JpaRepository<OptionQuote, Long>{
	
	List<OptionQuote> findBySymbol(Underlying symbol);

}

package com.spxvol.www.datastore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Heatmap {

	private List<OptionQuote> chains;

	private Map<LocalDate, Map<BigDecimal, List<OptionQuote>>> expirationMap;

	public Heatmap(List<OptionQuote> chains, boolean skipStrikes) {
		
		if(skipStrikes) {
			Map<BigDecimal, List<OptionQuote>> datesByStrike = chains.stream().collect(Collectors.groupingBy(OptionQuote::getStrikePrice));
			int maxDates = datesByStrike.values().stream().map(List::size).max(Comparator.naturalOrder()).get();
			chains = datesByStrike.values().stream().filter(list -> list.size() == maxDates).flatMap(List::stream)
					.collect(Collectors.toList());
		}
		
		this.chains = chains;
		this.expirationMap = chains.stream().collect(Collectors.groupingBy(OptionQuote::getExpiration,
				Collectors.groupingBy(OptionQuote::getStrikePrice)));
	}
	
	public List<LocalDate> getExpirationDates() {
		return chains.stream().map(OptionQuote::getExpiration).distinct().sorted().collect(Collectors.toList());
	}
	
	public List<Long> getExpirationHeadings() {
		return chains.stream().map(OptionQuote::getExpiration)
				.map(date -> date.toEpochDay() - LocalDate.now().toEpochDay())
				.distinct().sorted()
				.collect(Collectors.toList());
	}
	
	public List<BigDecimal> getStrikes() {
		return chains.stream().map(OptionQuote::getStrikePrice).distinct().sorted().collect(Collectors.toList());
	}
	
	public Double at(LocalDate expiration, BigDecimal strike) {
		final Map<BigDecimal, List<OptionQuote>> exp = expirationMap.get(expiration);
		if(exp == null) {
			return null;
		}
		final List<OptionQuote> list = exp.get(strike);
		if(list == null) {
			return null;
		}
		return list.stream().map(OptionQuote::getIv)
				.filter(x -> x != null)
				.mapToDouble(BigDecimal::doubleValue).map(x -> x*100).average().orElse(0);
	}

	private final Logger logger = Logger.getLogger(getClass().getName());

}

package com.spxvol.www.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.spxvol.www.datastore.OptionQuote;

public class Heatmap {

	private List<OptionQuote> chains;

	private Map<LocalDate, Map<BigDecimal, List<OptionQuote>>> expirationMap;

	private Map<BigDecimal, Map<LocalDate, List<OptionQuote>>> strikeMap;

	private double minIV;

	private double maxIV;

	public Heatmap(List<OptionQuote> chains, boolean skipStrikes) {

		if (skipStrikes) {
			Map<BigDecimal, List<OptionQuote>> datesByStrike = chains.stream()
					.collect(Collectors.groupingBy(OptionQuote::getStrikePrice));
			int maxDates = datesByStrike.values().stream().map(List::size).max(Comparator.naturalOrder()).get();
			chains = datesByStrike.values().stream().filter(list -> list.size() == maxDates).flatMap(List::stream)
					.collect(Collectors.toList());
		}

		this.chains = chains;
		this.expirationMap = chains.stream().collect(
				Collectors.groupingBy(OptionQuote::getExpiration, Collectors.groupingBy(OptionQuote::getStrikePrice)));
		this.strikeMap = chains.stream().collect(
				Collectors.groupingBy(OptionQuote::getStrikePrice, Collectors.groupingBy(OptionQuote::getExpiration)));
		
		this.minIV = chains.stream().map(OptionQuote::getIv).filter(x->x!=null).min(Comparator.naturalOrder()).get().doubleValue();
		this.maxIV = chains.stream().map(OptionQuote::getIv).filter(x->x!=null).max(Comparator.naturalOrder()).get().doubleValue();
	}

	public List<LocalDate> getExpirationDates() {
		return chains.stream().map(OptionQuote::getExpiration).distinct().sorted().collect(Collectors.toList());
	}

	public List<Long> getExpirationHeadings() {
		return chains.stream().map(OptionQuote::getExpiration)
				.map(date -> date.toEpochDay() - LocalDate.now().toEpochDay()).distinct().sorted()
				.collect(Collectors.toList());
	}

	public List<BigDecimal> getStrikes() {
		return chains.stream().map(OptionQuote::getStrikePrice).distinct().sorted().collect(Collectors.toList());
	}
	
	/**
	 * https://stackoverflow.com/questions/12875486/what-is-the-algorithm-to-create-colors-for-a-heatmap
	 * 
	 * @param normalizedValue0to1
	 * @return
	 */
	private String getHeatmapColorCSS(double normalizedValue0to1) {
		double h = (1 - normalizedValue0to1) * 240;
		double s = 100;
		double l = 50;
		return String.format("hsl(%.2f, %.2f%%, %.2f%%)", h, s, l);
	}
	
	public List<ColorfulValue> row(BigDecimal strike) {
		Map<LocalDate, List<OptionQuote>> dates = strikeMap.get(strike);
		
		return expirationMap.keySet().stream().sorted().map(date -> {
			final List<OptionQuote> dateValues = dates.get(date);
			if(dateValues != null) {
				OptionalDouble value = dateValues.stream().map(OptionQuote::getIv).filter(x -> x != null)
						.mapToDouble(BigDecimal::doubleValue).average();
				if(value.isPresent()) {
					double nValue = (value.getAsDouble() - minIV) / (maxIV - minIV);
					return new ColorfulValue(value.getAsDouble()*100, getHeatmapColorCSS(nValue));
				}
				return new ColorfulValue(null, "hsl(0,100,100)");
			}
			else {
				return new ColorfulValue(null, "hsl(0,50,50)");
			}
		}).collect(Collectors.toList());
	}

	private final Logger logger = Logger.getLogger(getClass().getName());

}

package com.spxvol.www.datastore;

import java.math.BigDecimal;

public class AggregationSummary {

	private String symbol;

	private Double averageImpliedVolatility;

	private Double lowestImpliedVolatility;

	private double highestImpliedVolatility;

	public AggregationSummary(String symbol, double averageImpliedVolatility, BigDecimal lowestImpliedVolatility,
			BigDecimal highestImpliedVolatility) {
		super();
		this.symbol = symbol;
		this.averageImpliedVolatility = averageImpliedVolatility;
		this.lowestImpliedVolatility = lowestImpliedVolatility.doubleValue();
		this.highestImpliedVolatility = highestImpliedVolatility.doubleValue();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getAverageImpliedVolatility() {
		return averageImpliedVolatility;
	}

	public void setAverageImpliedVolatility(Double averageImpliedVolatility) {
		this.averageImpliedVolatility = averageImpliedVolatility;
	}

	public Double getLowestImpliedVolatility() {
		return lowestImpliedVolatility;
	}

	public void setLowestImpliedVolatility(Double lowestImpliedVolatility) {
		this.lowestImpliedVolatility = lowestImpliedVolatility;
	}

	public double getHighestImpliedVolatility() {
		return highestImpliedVolatility;
	}

	public void setHighestImpliedVolatility(double highestImpliedVolatility) {
		this.highestImpliedVolatility = highestImpliedVolatility;
	}

	@Override
	public String toString() {
		return String.format(
				"AggregationSummary [symbol=%s, averageImpliedVolatility=%s, lowestImpliedVolatility=%s, highestImpliedVolatility=%s]",
				symbol, averageImpliedVolatility, lowestImpliedVolatility, highestImpliedVolatility);
	}

}

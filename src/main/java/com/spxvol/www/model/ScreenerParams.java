package com.spxvol.www.model;

public class ScreenerParams {

	private String symbols;

	private Float minDelta;

	private Float maxDelta;

	private Float minDaysToExpiration;

	private Float maxDaysToExpiration;

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	public Float getMinDelta() {
		return minDelta;
	}

	public void setMinDelta(Float minDelta) {
		this.minDelta = minDelta;
	}

	public Float getMaxDelta() {
		return maxDelta;
	}

	public void setMaxDelta(Float maxDelta) {
		this.maxDelta = maxDelta;
	}

	public Float getMaxDaysToExpiration() {
		return maxDaysToExpiration;
	}

	public void setMaxDaysToExpiration(Float maxDaysToExpiration) {
		this.maxDaysToExpiration = maxDaysToExpiration;
	}

	public Float getMinDaysToExpiration() {
		return minDaysToExpiration;
	}

	public void setMinDaysToExpiration(Float minDaysToExpiration) {
		this.minDaysToExpiration = minDaysToExpiration;
	}

}

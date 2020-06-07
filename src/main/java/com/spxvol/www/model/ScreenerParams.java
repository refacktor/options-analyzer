package com.spxvol.www.model;

public class ScreenerParams {

	private String symbols;

	private Float minProbability;

	private Float maxProbability;

	private Integer minDays;

	private Integer maxDays;

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	public Float getMinProbability() {
		return minProbability;
	}

	public void setMinProbability(Float minProbability) {
		this.minProbability = minProbability;
	}

	public Float getMaxProbability() {
		return maxProbability;
	}

	public void setMaxProbability(Float maxProbability) {
		this.maxProbability = maxProbability;
	}

	public Integer getMinDays() {
		return minDays;
	}

	public void setMinDays(Integer minDays) {
		this.minDays = minDays;
	}

	public Integer getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(Integer maxDays) {
		this.maxDays = maxDays;
	}

}

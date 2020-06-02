package com.spxvol.www.model;

public class ScreenerParams {

	private String symbols;

	private Float minDelta;

	private Float maxDelta;

	private Integer minDays;

	private Integer maxDays;

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

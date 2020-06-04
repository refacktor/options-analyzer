package com.spxvol.www.model;

public class SymbolSearch {

	private String symbol;

	private Boolean skipStrikes;

	public SymbolSearch() {

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Boolean getSkipStrikes() {
		return skipStrikes;
	}

	public void setSkipStrikes(Boolean skipStrikes) {
		this.skipStrikes = skipStrikes;
	}

}
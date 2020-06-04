package com.spxvol.www.model;

public class SymbolSearch {

	private String symbol;

	private boolean skipStrikes;

	public SymbolSearch() {

	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isSkipStrikes() {
		return skipStrikes;
	}

	public void setSkipStrikes(boolean skipStrikes) {
		this.skipStrikes = skipStrikes;
	}

}
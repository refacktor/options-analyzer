package com.spxvol.datasources.iexcloud;

import java.time.LocalDate;
import java.util.Currency;

public class Symbols {
	private String symbol;
	private String exchange;
	private String name;
	private LocalDate date;
	private String type;
	private String iexId;
	private String region;
	private Currency currency;
	private boolean isEnabled;
	private String figi;
	private String cik;

	public Symbols() {
	}

	public Symbols(String symbol, String exchange, String name, LocalDate date, String type, String iexId,
			String region, Currency currency, boolean isEnabled, String figi, String cik) {
		super();
		this.symbol = symbol;
		this.exchange = exchange;
		this.name = name;
		this.date = date;
		this.type = type;
		this.iexId = iexId;
		this.region = region;
		this.currency = currency;
		this.isEnabled = isEnabled;
		this.figi = figi;
		this.cik = cik;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIexId() {
		return iexId;
	}

	public void setIexId(String iexId) {
		this.iexId = iexId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getFigi() {
		return figi;
	}

	public void setFigi(String figi) {
		this.figi = figi;
	}

	public String getCik() {
		return cik;
	}

	public void setCik(String cik) {
		this.cik = cik;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symbols other = (Symbols) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Symbols [symbol=" + symbol + ", exchange=" + exchange + ", name=" + name + ", date=" + date
				+ ", type=" + type + ", iexId=" + iexId + ", region=" + region + ", currency=" + currency
				+ ", isEnabled=" + isEnabled + ", figi=" + figi + ", cik=" + cik + "]";
	}

}
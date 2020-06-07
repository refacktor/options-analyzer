package com.spxvol.www.datastore;

import java.time.Instant;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.spxvol.www.model.Constants;

@Entity
public class Underlying {

	@Id private String symbol;

	private double price;

	private Instant lastTrade;

	public Underlying() {
	}

	public Underlying(String symbol) {
		super();
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Instant getLastTrade() {
		return lastTrade;
	}

	public void setLastTrade(Instant lastTrade) {
		this.lastTrade = lastTrade;
	}

	public ZonedDateTime getLastTradeTZ() {
		return lastTrade.atZone(Constants.MARKET_TIME_ZONE);
	}

}

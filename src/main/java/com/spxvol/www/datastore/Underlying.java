package com.spxvol.www.datastore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Underlying {

	@Id private String symbol;

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

}

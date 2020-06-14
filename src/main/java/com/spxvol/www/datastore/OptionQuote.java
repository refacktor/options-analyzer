package com.spxvol.www.datastore;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionQuote {

	@Id @SequenceGenerator(name = "optionPairSeqGen", sequenceName = "pairSeq", initialValue = 1, allocationSize = 20000) @GeneratedValue(generator = "optionPairSeqGen") private Long id;

	private LocalDate expiration;

	private String displaySymbol;

	private String optionType;

	private Double strikePrice;

	private String symbol;

	private Double bid;

	private Double ask;

	private long bidSize;

	private long askSize;

	private String inTheMoney;

	private long volume;

	private int openInterest;

	private Double netChange;

	private Double lastPrice;

	private String osiKey;

	// OptionGreeks
	private double rho;

	private double vega;

	private double theta;

	private double delta;

	private double gamma;

	private double iv;

	// Computed Values
	private double bidIV;

	private double askIV;

	public OptionQuote() {
	}
	
	@Access(AccessType.PROPERTY)
	public double getProbability() {
		return Math.abs(delta);
	}
	
	public void setProbability(double computed) {
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public String getOptionType() {
		return optionType;
	}

	public Double getStrikePrice() {
		return strikePrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getBid() {
		return bid;
	}

	public Double getAsk() {
		return ask;
	}

	public long getBidSize() {
		return bidSize;
	}

	public long getAskSize() {
		return askSize;
	}

	public String getInTheMoney() {
		return inTheMoney;
	}

	public long getVolume() {
		return volume;
	}

	public int getOpenInterest() {
		return openInterest;
	}

	public Double getNetChange() {
		return netChange;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public String getOsiKey() {
		return osiKey;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public void setBidSize(long bidSize) {
		this.bidSize = bidSize;
	}

	public void setAskSize(long askSize) {
		this.askSize = askSize;
	}

	public void setInTheMoney(String inTheMoney) {
		this.inTheMoney = inTheMoney;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public void setOpenInterest(int openInterest) {
		this.openInterest = openInterest;
	}

	public void setNetChange(Double netChange) {
		this.netChange = netChange;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setOsiKey(String osiKey) {
		this.osiKey = osiKey;
	}

	public LocalDate getDate() {
		return expiration;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public double getVega() {
		return vega;
	}

	public void setVega(double vega) {
		this.vega = vega;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public double getIv() {
		return iv;
	}

	public void setIv(double iv) {
		this.iv = iv;
	}

	public double getBidIV() {
		return bidIV;
	}

	public void setBidIV(double bidIV) {
		this.bidIV = bidIV;
	}

	public double getAskIV() {
		return askIV;
	}

	public void setAskIV(double askIV) {
		this.askIV = askIV;
	}

	@Override
	public String toString() {
		return displaySymbol;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.displaySymbol.equals(obj.toString());
	}

	@Override
	public int hashCode() {
		return displaySymbol.hashCode();
	}
}

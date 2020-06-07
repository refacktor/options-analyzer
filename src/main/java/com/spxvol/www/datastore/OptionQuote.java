package com.spxvol.www.datastore;

import java.math.BigDecimal;
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

	@Version private Integer version;

	private long uniquePair;

	private String optionCategory;

	private String optionRootSymbol;

	private LocalDate expiration;

	private boolean adjustedFlag;

	private String displaySymbol;

	private String optionType;

	private BigDecimal strikePrice;

	private String symbol;

	private BigDecimal bid;

	private BigDecimal ask;

	private long bidSize;

	private long askSize;

	private String inTheMoney;

	private long volume;

	private int openInterest;

	private BigDecimal netChange;

	private BigDecimal lastPrice;

	private String quoteDetail;

	private String osiKey;

	// OptionGreeks
	private double rho;

	private double vega;

	private double theta;

	private double delta;

	private double gamma;

	private double iv;

	private boolean currentValue;

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

	public long getUniquePair() {
		return uniquePair;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOptionCategory() {
		return optionCategory;
	}

	public String getOptionRootSymbol() {
		return optionRootSymbol;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public boolean isAdjustedFlag() {
		return adjustedFlag;
	}

	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public String getOptionType() {
		return optionType;
	}

	public BigDecimal getStrikePrice() {
		return strikePrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public BigDecimal getAsk() {
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

	public BigDecimal getNetChange() {
		return netChange;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public String getQuoteDetail() {
		return quoteDetail;
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

	public void setUniquePair(long uniquePair) {
		this.uniquePair = uniquePair;
	}

	public void setOptionCategory(String optionCategory) {
		this.optionCategory = optionCategory;
	}

	public void setOptionRootSymbol(String optionRootSymbol) {
		this.optionRootSymbol = optionRootSymbol;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public void setAdjustedFlag(boolean adjustedFlag) {
		this.adjustedFlag = adjustedFlag;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public void setStrikePrice(BigDecimal strikePrice) {
		this.strikePrice = strikePrice;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public void setAsk(BigDecimal ask) {
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

	public void setNetChange(BigDecimal netChange) {
		this.netChange = netChange;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setQuoteDetail(String quoteDetail) {
		this.quoteDetail = quoteDetail;
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

	public boolean isCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(boolean currentValue) {
		this.currentValue = currentValue;
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

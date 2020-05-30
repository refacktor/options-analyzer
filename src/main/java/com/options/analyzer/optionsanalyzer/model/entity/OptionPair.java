package com.options.analyzer.optionsanalyzer.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class OptionPair {
	@Id
	@SequenceGenerator(name = "optionPairSeqGen", sequenceName = "pairSeq", initialValue = 1, allocationSize = 20000)
	@GeneratedValue(generator = "optionPairSeqGen")
	private Long id;
	@Version
	private Integer version;

	private long uniquePair;
	private String optionCategory;
	private String optionRootSymbol;
	private LocalDateTime timeStamp;
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
	private BigDecimal openInterest;
	private BigDecimal netChange;
	private BigDecimal lastPrice;
	private String quoteDetail;
	private String osiKey;

	// OptionGreeks

	private BigDecimal rho;
	private BigDecimal vega;
	private BigDecimal theta;
	private BigDecimal delta;
	private BigDecimal gamma;
	private BigDecimal iv;
	private boolean currentValue;

	public OptionPair() {
	}

	public OptionPair(long uniquePair, String optionCategory, String optionRootSymbol, LocalDateTime timeStamp,
			boolean adjustedFlag, String displaySymbol, String optionType, BigDecimal strikePrice, String symbol,
			BigDecimal bid, BigDecimal ask, long bidSize, long askSize, String inTheMoney, long volume,
			BigDecimal openInterest, BigDecimal netChange, BigDecimal lastPrice, String quoteDetail, String osiKey,
			BigDecimal rho, BigDecimal vega, BigDecimal theta, BigDecimal delta, BigDecimal gamma, BigDecimal iv,
			boolean currentValue) {
		super();
		this.uniquePair = uniquePair;
		this.optionCategory = optionCategory;
		this.optionRootSymbol = optionRootSymbol;
		this.timeStamp = timeStamp;
		this.adjustedFlag = adjustedFlag;
		this.displaySymbol = displaySymbol;
		this.optionType = optionType;
		this.strikePrice = strikePrice;
		this.symbol = symbol;
		this.bid = bid;
		this.ask = ask;
		this.bidSize = bidSize;
		this.askSize = askSize;
		this.inTheMoney = inTheMoney;
		this.volume = volume;
		this.openInterest = openInterest;
		this.netChange = netChange;
		this.lastPrice = lastPrice;
		this.quoteDetail = quoteDetail;
		this.osiKey = osiKey;
		this.rho = rho;
		this.vega = vega;
		this.theta = theta;
		this.delta = delta;
		this.gamma = gamma;
		this.iv = iv;
		this.currentValue = currentValue;
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

	public LocalDateTime getTimeStamp() {
		return timeStamp;
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

	public BigDecimal getOpenInterest() {
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

	public BigDecimal getRho() {
		return rho;
	}

	public void setRho(BigDecimal rho) {
		this.rho = rho;
	}

	public BigDecimal getVega() {
		return vega;
	}

	public void setVega(BigDecimal vega) {
		this.vega = vega;
	}

	public BigDecimal getTheta() {
		return theta;
	}

	public void setTheta(BigDecimal theta) {
		this.theta = theta;
	}

	public BigDecimal getDelta() {
		return delta;
	}

	public void setDelta(BigDecimal delta) {
		this.delta = delta;
	}

	public BigDecimal getGamma() {
		return gamma;
	}

	public void setGamma(BigDecimal gamma) {
		this.gamma = gamma;
	}

	public BigDecimal getIv() {
		return iv;
	}

	public void setIv(BigDecimal iv) {
		this.iv = iv;
	}

	public boolean isCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(boolean currentValue) {
		this.currentValue = currentValue;
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

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
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

	public void setOpenInterest(BigDecimal openInterest) {
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
		return timeStamp.toLocalDate();
	}

	public static OptionPair getOptionPair(LocalDateTime expiration, long uniquePair, JsonNode jsonNode) {
		JsonNode optionGreeksNode = jsonNode.get("OptionGreeks");
		return new OptionPair(uniquePair, jsonNode.get("optionCategory").asText(),
				jsonNode.get("optionRootSymbol").asText(),
				expiration,
				jsonNode.get("adjustedFlag").asBoolean(), jsonNode.get("displaySymbol").asText(),
				jsonNode.get("optionType").asText(), BigDecimal.valueOf(jsonNode.get("strikePrice").asDouble()),
				jsonNode.get("symbol").asText(), BigDecimal.valueOf(jsonNode.get("bid").asDouble()),
				BigDecimal.valueOf(jsonNode.get("ask").asDouble()), jsonNode.get("bidSize").asLong(),
				jsonNode.get("askSize").asLong(), jsonNode.get("inTheMoney").asText(), jsonNode.get("volume").asLong(),
				BigDecimal.valueOf(jsonNode.get("openInterest").asDouble()),
				BigDecimal.valueOf(jsonNode.get("netChange").asDouble()),
				BigDecimal.valueOf(jsonNode.get("lastPrice").asDouble()), jsonNode.get("quoteDetail").asText(),
				jsonNode.get("osiKey").asText(), BigDecimal.valueOf(optionGreeksNode.get("rho").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("vega").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("theta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("delta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("gamma").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("iv").asDouble()),
				optionGreeksNode.get("currentValue").asBoolean());
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adjustedFlag ? 1231 : 1237);
		result = prime * result + ((ask == null) ? 0 : ask.hashCode());
		result = prime * result + (int) (askSize ^ (askSize >>> 32));
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + (int) (bidSize ^ (bidSize >>> 32));
		result = prime * result + (currentValue ? 1231 : 1237);
		result = prime * result + ((delta == null) ? 0 : delta.hashCode());
		result = prime * result + ((displaySymbol == null) ? 0 : displaySymbol.hashCode());
		result = prime * result + ((gamma == null) ? 0 : gamma.hashCode());
		result = prime * result + ((inTheMoney == null) ? 0 : inTheMoney.hashCode());
		result = prime * result + ((iv == null) ? 0 : iv.hashCode());
		result = prime * result + ((lastPrice == null) ? 0 : lastPrice.hashCode());
		result = prime * result + ((netChange == null) ? 0 : netChange.hashCode());
		result = prime * result + ((openInterest == null) ? 0 : openInterest.hashCode());
		result = prime * result + ((optionCategory == null) ? 0 : optionCategory.hashCode());
		result = prime * result + ((optionRootSymbol == null) ? 0 : optionRootSymbol.hashCode());
		result = prime * result + ((optionType == null) ? 0 : optionType.hashCode());
		result = prime * result + ((osiKey == null) ? 0 : osiKey.hashCode());
		result = prime * result + ((quoteDetail == null) ? 0 : quoteDetail.hashCode());
		result = prime * result + ((rho == null) ? 0 : rho.hashCode());
		result = prime * result + ((strikePrice == null) ? 0 : strikePrice.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((theta == null) ? 0 : theta.hashCode());
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
		result = prime * result + ((vega == null) ? 0 : vega.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + (int) (volume ^ (volume >>> 32));
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
		OptionPair other = (OptionPair) obj;
		if (adjustedFlag != other.adjustedFlag)
			return false;
		if (ask == null) {
			if (other.ask != null)
				return false;
		} else if (!ask.equals(other.ask))
			return false;
		if (askSize != other.askSize)
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (bidSize != other.bidSize)
			return false;
		if (currentValue != other.currentValue)
			return false;
		if (delta == null) {
			if (other.delta != null)
				return false;
		} else if (!delta.equals(other.delta))
			return false;
		if (displaySymbol == null) {
			if (other.displaySymbol != null)
				return false;
		} else if (!displaySymbol.equals(other.displaySymbol))
			return false;
		if (gamma == null) {
			if (other.gamma != null)
				return false;
		} else if (!gamma.equals(other.gamma))
			return false;
		if (inTheMoney == null) {
			if (other.inTheMoney != null)
				return false;
		} else if (!inTheMoney.equals(other.inTheMoney))
			return false;
		if (iv == null) {
			if (other.iv != null)
				return false;
		} else if (!iv.equals(other.iv))
			return false;
		if (lastPrice == null) {
			if (other.lastPrice != null)
				return false;
		} else if (!lastPrice.equals(other.lastPrice))
			return false;
		if (netChange == null) {
			if (other.netChange != null)
				return false;
		} else if (!netChange.equals(other.netChange))
			return false;
		if (openInterest == null) {
			if (other.openInterest != null)
				return false;
		} else if (!openInterest.equals(other.openInterest))
			return false;
		if (optionCategory == null) {
			if (other.optionCategory != null)
				return false;
		} else if (!optionCategory.equals(other.optionCategory))
			return false;
		if (optionRootSymbol == null) {
			if (other.optionRootSymbol != null)
				return false;
		} else if (!optionRootSymbol.equals(other.optionRootSymbol))
			return false;
		if (optionType == null) {
			if (other.optionType != null)
				return false;
		} else if (!optionType.equals(other.optionType))
			return false;
		if (osiKey == null) {
			if (other.osiKey != null)
				return false;
		} else if (!osiKey.equals(other.osiKey))
			return false;
		if (quoteDetail == null) {
			if (other.quoteDetail != null)
				return false;
		} else if (!quoteDetail.equals(other.quoteDetail))
			return false;
		if (rho == null) {
			if (other.rho != null)
				return false;
		} else if (!rho.equals(other.rho))
			return false;
		if (strikePrice == null) {
			if (other.strikePrice != null)
				return false;
		} else if (!strikePrice.equals(other.strikePrice))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (theta == null) {
			if (other.theta != null)
				return false;
		} else if (!theta.equals(other.theta))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		if (vega == null) {
			if (other.vega != null)
				return false;
		} else if (!vega.equals(other.vega))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OptionPair [optionCategory=" + optionCategory + ", optionRootSymbol=" + optionRootSymbol
				+ ", timeStamp=" + timeStamp + ", adjustedFlag=" + adjustedFlag + ", displaySymbol=" + displaySymbol
				+ ", optionType=" + optionType + ", strikePrice=" + strikePrice + ", symbol=" + symbol + ", bid=" + bid
				+ ", ask=" + ask + ", bidSize=" + bidSize + ", askSize=" + askSize + ", inTheMoney=" + inTheMoney
				+ ", volume=" + volume + ", openInterest=" + openInterest + ", netChange=" + netChange + ", lastPrice="
				+ lastPrice + ", quoteDetail=" + quoteDetail + ", osiKey=" + osiKey + "]";
	}

}

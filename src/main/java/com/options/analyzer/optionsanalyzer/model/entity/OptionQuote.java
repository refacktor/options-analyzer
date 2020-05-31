package com.options.analyzer.optionsanalyzer.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class OptionQuote {
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

	public OptionQuote() {
	}

	public OptionQuote(long uniquePair, String optionCategory, String optionRootSymbol, LocalDateTime timeStamp,
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

	public static OptionQuote from(LocalDateTime expiration, long uniquePair, JsonNode jsonNode) {
		JsonNode optionGreeksNode = jsonNode.get("OptionGreeks");
		return new OptionQuote(uniquePair, jsonNode.get("optionCategory").asText(),
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
		return Objects.hash(adjustedFlag, ask, askSize, bid, bidSize, currentValue, delta, displaySymbol, gamma, id,
				inTheMoney, iv, lastPrice, netChange, openInterest, optionCategory, optionRootSymbol, optionType,
				osiKey, quoteDetail, rho, strikePrice, symbol, theta, timeStamp, uniquePair, vega, version, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OptionQuote other = (OptionQuote) obj;
		return adjustedFlag == other.adjustedFlag && Objects.equals(ask, other.ask) && askSize == other.askSize
				&& Objects.equals(bid, other.bid) && bidSize == other.bidSize && currentValue == other.currentValue
				&& Objects.equals(delta, other.delta) && Objects.equals(displaySymbol, other.displaySymbol)
				&& Objects.equals(gamma, other.gamma) && Objects.equals(id, other.id)
				&& Objects.equals(inTheMoney, other.inTheMoney) && Objects.equals(iv, other.iv)
				&& Objects.equals(lastPrice, other.lastPrice) && Objects.equals(netChange, other.netChange)
				&& Objects.equals(openInterest, other.openInterest)
				&& Objects.equals(optionCategory, other.optionCategory)
				&& Objects.equals(optionRootSymbol, other.optionRootSymbol)
				&& Objects.equals(optionType, other.optionType) && Objects.equals(osiKey, other.osiKey)
				&& Objects.equals(quoteDetail, other.quoteDetail) && Objects.equals(rho, other.rho)
				&& Objects.equals(strikePrice, other.strikePrice) && Objects.equals(symbol, other.symbol)
				&& Objects.equals(theta, other.theta) && Objects.equals(timeStamp, other.timeStamp)
				&& uniquePair == other.uniquePair && Objects.equals(vega, other.vega)
				&& Objects.equals(version, other.version) && volume == other.volume;
	}

	@Override
	public String toString() {
		return String.format(
				"OptionQuote [id=%s, version=%s, uniquePair=%s, optionCategory=%s, optionRootSymbol=%s, timeStamp=%s, adjustedFlag=%s, displaySymbol=%s, optionType=%s, strikePrice=%s, symbol=%s, bid=%s, ask=%s, bidSize=%s, askSize=%s, inTheMoney=%s, volume=%s, openInterest=%s, netChange=%s, lastPrice=%s, quoteDetail=%s, osiKey=%s, rho=%s, vega=%s, theta=%s, delta=%s, gamma=%s, iv=%s, currentValue=%s]",
				id, version, uniquePair, optionCategory, optionRootSymbol, timeStamp, adjustedFlag, displaySymbol,
				optionType, strikePrice, symbol, bid, ask, bidSize, askSize, inTheMoney, volume, openInterest,
				netChange, lastPrice, quoteDetail, osiKey, rho, vega, theta, delta, gamma, iv, currentValue);
	}

}

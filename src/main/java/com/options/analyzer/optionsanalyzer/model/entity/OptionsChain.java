package com.options.analyzer.optionsanalyzer.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class OptionsChain{
    @Id
    private String id;
    private String symbol;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate expirationDate;
    private long contractSize;
    private BigDecimal strikePrice;
    private BigDecimal closingPrice;
    private String side;
    private String type;
    private BigDecimal volume;
    private BigDecimal openInterest;
    private BigDecimal bid;
    private BigDecimal ask;
    private LocalDate lastUpdated;
    private boolean isAdjusted;

    public OptionsChain() {
    }

    public OptionsChain(String id,
                        String symbol,
                        LocalDate expirationDate,
                        long contractSize,
                        BigDecimal strikePrice,
                        BigDecimal closingPrice,
                        String side,
                        String type,
                        BigDecimal volume,
                        BigDecimal openInterest,
                        BigDecimal bid,
                        BigDecimal ask,
                        LocalDate lastUpdated,
                        boolean isAdjusted) {

        this.id = id;
        this.symbol = symbol;
        this.expirationDate = expirationDate;
        this.contractSize = contractSize;
        this.strikePrice = strikePrice;
        this.closingPrice = closingPrice;
        this.side = side;
        this.type = type;
        this.volume = volume;
        this.openInterest = openInterest;
        this.bid = bid;
        this.ask = ask;
        this.lastUpdated = lastUpdated;
        this.isAdjusted = isAdjusted;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getContractSize() {
        return contractSize;
    }

    public void setContractSize(long contractSize) {
        this.contractSize = contractSize;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(BigDecimal openInterest) {
        this.openInterest = openInterest;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public boolean isAdjusted() {
        return isAdjusted;
    }

    public void setIsAdjusted(boolean isAdjusted) {
        this.isAdjusted = isAdjusted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsChain that = (OptionsChain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OptionsChain{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", contractSize=" + contractSize +
                ", strikePrice=" + strikePrice +
                ", closingPrice=" + closingPrice +
                ", side='" + side + '\'' +
                ", type='" + type + '\'' +
                ", volume=" + volume +
                ", openInterest=" + openInterest +
                ", bid=" + bid +
                ", ask=" + ask +
                ", lastUpdated=" + lastUpdated +
                ", isAdjusted=" + isAdjusted +
                '}';
    }
}

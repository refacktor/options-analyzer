
package com.spxvol.jsonschema2pojo.yahoo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "language",
    "region",
    "quoteType",
    "triggerable",
    "currency",
    "priceHint",
    "regularMarketChange",
    "regularMarketChangePercent",
    "regularMarketTime",
    "regularMarketPrice",
    "regularMarketDayHigh",
    "regularMarketDayRange",
    "regularMarketDayLow",
    "regularMarketVolume",
    "regularMarketPreviousClose",
    "bid",
    "ask",
    "bidSize",
    "askSize",
    "exchange",
    "market",
    "fullExchangeName",
    "shortName",
    "regularMarketOpen",
    "fiftyTwoWeekLowChange",
    "fiftyTwoWeekLowChangePercent",
    "fiftyTwoWeekRange",
    "fiftyTwoWeekHighChange",
    "fiftyTwoWeekHighChangePercent",
    "fiftyTwoWeekLow",
    "fiftyTwoWeekHigh",
    "marketState",
    "underlyingSymbol",
    "sourceInterval",
    "exchangeDataDelayedBy",
    "exchangeTimezoneName",
    "exchangeTimezoneShortName",
    "gmtOffSetMilliseconds",
    "esgPopulated",
    "tradeable",
    "symbol"
})
public class Quote {

    @JsonProperty("language")
    private String language;
    @JsonProperty("region")
    private String region;
    @JsonProperty("quoteType")
    private String quoteType;
    @JsonProperty("triggerable")
    private Boolean triggerable;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("priceHint")
    private Long priceHint;
    @JsonProperty("regularMarketChange")
    private Double regularMarketChange;
    @JsonProperty("regularMarketChangePercent")
    private Double regularMarketChangePercent;
    @JsonProperty("regularMarketTime")
    private Long regularMarketTime;
    @JsonProperty("regularMarketPrice")
    private Double regularMarketPrice;
    @JsonProperty("regularMarketDayHigh")
    private Double regularMarketDayHigh;
    @JsonProperty("regularMarketDayRange")
    private String regularMarketDayRange;
    @JsonProperty("regularMarketDayLow")
    private Double regularMarketDayLow;
    @JsonProperty("regularMarketVolume")
    private Long regularMarketVolume;
    @JsonProperty("regularMarketPreviousClose")
    private Double regularMarketPreviousClose;
    @JsonProperty("bid")
    private Double bid;
    @JsonProperty("ask")
    private Double ask;
    @JsonProperty("bidSize")
    private Long bidSize;
    @JsonProperty("askSize")
    private Long askSize;
    @JsonProperty("exchange")
    private String exchange;
    @JsonProperty("market")
    private String market;
    @JsonProperty("fullExchangeName")
    private String fullExchangeName;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("regularMarketOpen")
    private Double regularMarketOpen;
    @JsonProperty("fiftyTwoWeekLowChange")
    private Double fiftyTwoWeekLowChange;
    @JsonProperty("fiftyTwoWeekLowChangePercent")
    private Double fiftyTwoWeekLowChangePercent;
    @JsonProperty("fiftyTwoWeekRange")
    private String fiftyTwoWeekRange;
    @JsonProperty("fiftyTwoWeekHighChange")
    private Double fiftyTwoWeekHighChange;
    @JsonProperty("fiftyTwoWeekHighChangePercent")
    private Double fiftyTwoWeekHighChangePercent;
    @JsonProperty("fiftyTwoWeekLow")
    private Double fiftyTwoWeekLow;
    @JsonProperty("fiftyTwoWeekHigh")
    private Double fiftyTwoWeekHigh;
    @JsonProperty("marketState")
    private String marketState;
    @JsonProperty("underlyingSymbol")
    private String underlyingSymbol;
    @JsonProperty("sourceInterval")
    private Long sourceInterval;
    @JsonProperty("exchangeDataDelayedBy")
    private Long exchangeDataDelayedBy;
    @JsonProperty("exchangeTimezoneName")
    private String exchangeTimezoneName;
    @JsonProperty("exchangeTimezoneShortName")
    private String exchangeTimezoneShortName;
    @JsonProperty("gmtOffSetMilliseconds")
    private Long gmtOffSetMilliseconds;
    @JsonProperty("esgPopulated")
    private Boolean esgPopulated;
    @JsonProperty("tradeable")
    private Boolean tradeable;
    @JsonProperty("symbol")
    private String symbol;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("quoteType")
    public String getQuoteType() {
        return quoteType;
    }

    @JsonProperty("quoteType")
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    @JsonProperty("triggerable")
    public Boolean getTriggerable() {
        return triggerable;
    }

    @JsonProperty("triggerable")
    public void setTriggerable(Boolean triggerable) {
        this.triggerable = triggerable;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("priceHint")
    public Long getPriceHint() {
        return priceHint;
    }

    @JsonProperty("priceHint")
    public void setPriceHint(Long priceHint) {
        this.priceHint = priceHint;
    }

    @JsonProperty("regularMarketChange")
    public Double getRegularMarketChange() {
        return regularMarketChange;
    }

    @JsonProperty("regularMarketChange")
    public void setRegularMarketChange(Double regularMarketChange) {
        this.regularMarketChange = regularMarketChange;
    }

    @JsonProperty("regularMarketChangePercent")
    public Double getRegularMarketChangePercent() {
        return regularMarketChangePercent;
    }

    @JsonProperty("regularMarketChangePercent")
    public void setRegularMarketChangePercent(Double regularMarketChangePercent) {
        this.regularMarketChangePercent = regularMarketChangePercent;
    }

    @JsonProperty("regularMarketTime")
    public Long getRegularMarketTime() {
        return regularMarketTime;
    }

    @JsonProperty("regularMarketTime")
    public void setRegularMarketTime(Long regularMarketTime) {
        this.regularMarketTime = regularMarketTime;
    }

    @JsonProperty("regularMarketPrice")
    public Double getRegularMarketPrice() {
        return regularMarketPrice;
    }

    @JsonProperty("regularMarketPrice")
    public void setRegularMarketPrice(Double regularMarketPrice) {
        this.regularMarketPrice = regularMarketPrice;
    }

    @JsonProperty("regularMarketDayHigh")
    public Double getRegularMarketDayHigh() {
        return regularMarketDayHigh;
    }

    @JsonProperty("regularMarketDayHigh")
    public void setRegularMarketDayHigh(Double regularMarketDayHigh) {
        this.regularMarketDayHigh = regularMarketDayHigh;
    }

    @JsonProperty("regularMarketDayRange")
    public String getRegularMarketDayRange() {
        return regularMarketDayRange;
    }

    @JsonProperty("regularMarketDayRange")
    public void setRegularMarketDayRange(String regularMarketDayRange) {
        this.regularMarketDayRange = regularMarketDayRange;
    }

    @JsonProperty("regularMarketDayLow")
    public Double getRegularMarketDayLow() {
        return regularMarketDayLow;
    }

    @JsonProperty("regularMarketDayLow")
    public void setRegularMarketDayLow(Double regularMarketDayLow) {
        this.regularMarketDayLow = regularMarketDayLow;
    }

    @JsonProperty("regularMarketVolume")
    public Long getRegularMarketVolume() {
        return regularMarketVolume;
    }

    @JsonProperty("regularMarketVolume")
    public void setRegularMarketVolume(Long regularMarketVolume) {
        this.regularMarketVolume = regularMarketVolume;
    }

    @JsonProperty("regularMarketPreviousClose")
    public Double getRegularMarketPreviousClose() {
        return regularMarketPreviousClose;
    }

    @JsonProperty("regularMarketPreviousClose")
    public void setRegularMarketPreviousClose(Double regularMarketPreviousClose) {
        this.regularMarketPreviousClose = regularMarketPreviousClose;
    }

    @JsonProperty("bid")
    public Double getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(Double bid) {
        this.bid = bid;
    }

    @JsonProperty("ask")
    public Double getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @JsonProperty("bidSize")
    public Long getBidSize() {
        return bidSize;
    }

    @JsonProperty("bidSize")
    public void setBidSize(Long bidSize) {
        this.bidSize = bidSize;
    }

    @JsonProperty("askSize")
    public Long getAskSize() {
        return askSize;
    }

    @JsonProperty("askSize")
    public void setAskSize(Long askSize) {
        this.askSize = askSize;
    }

    @JsonProperty("exchange")
    public String getExchange() {
        return exchange;
    }

    @JsonProperty("exchange")
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @JsonProperty("market")
    public String getMarket() {
        return market;
    }

    @JsonProperty("market")
    public void setMarket(String market) {
        this.market = market;
    }

    @JsonProperty("fullExchangeName")
    public String getFullExchangeName() {
        return fullExchangeName;
    }

    @JsonProperty("fullExchangeName")
    public void setFullExchangeName(String fullExchangeName) {
        this.fullExchangeName = fullExchangeName;
    }

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("regularMarketOpen")
    public Double getRegularMarketOpen() {
        return regularMarketOpen;
    }

    @JsonProperty("regularMarketOpen")
    public void setRegularMarketOpen(Double regularMarketOpen) {
        this.regularMarketOpen = regularMarketOpen;
    }

    @JsonProperty("fiftyTwoWeekLowChange")
    public Double getFiftyTwoWeekLowChange() {
        return fiftyTwoWeekLowChange;
    }

    @JsonProperty("fiftyTwoWeekLowChange")
    public void setFiftyTwoWeekLowChange(Double fiftyTwoWeekLowChange) {
        this.fiftyTwoWeekLowChange = fiftyTwoWeekLowChange;
    }

    @JsonProperty("fiftyTwoWeekLowChangePercent")
    public Double getFiftyTwoWeekLowChangePercent() {
        return fiftyTwoWeekLowChangePercent;
    }

    @JsonProperty("fiftyTwoWeekLowChangePercent")
    public void setFiftyTwoWeekLowChangePercent(Double fiftyTwoWeekLowChangePercent) {
        this.fiftyTwoWeekLowChangePercent = fiftyTwoWeekLowChangePercent;
    }

    @JsonProperty("fiftyTwoWeekRange")
    public String getFiftyTwoWeekRange() {
        return fiftyTwoWeekRange;
    }

    @JsonProperty("fiftyTwoWeekRange")
    public void setFiftyTwoWeekRange(String fiftyTwoWeekRange) {
        this.fiftyTwoWeekRange = fiftyTwoWeekRange;
    }

    @JsonProperty("fiftyTwoWeekHighChange")
    public Double getFiftyTwoWeekHighChange() {
        return fiftyTwoWeekHighChange;
    }

    @JsonProperty("fiftyTwoWeekHighChange")
    public void setFiftyTwoWeekHighChange(Double fiftyTwoWeekHighChange) {
        this.fiftyTwoWeekHighChange = fiftyTwoWeekHighChange;
    }

    @JsonProperty("fiftyTwoWeekHighChangePercent")
    public Double getFiftyTwoWeekHighChangePercent() {
        return fiftyTwoWeekHighChangePercent;
    }

    @JsonProperty("fiftyTwoWeekHighChangePercent")
    public void setFiftyTwoWeekHighChangePercent(Double fiftyTwoWeekHighChangePercent) {
        this.fiftyTwoWeekHighChangePercent = fiftyTwoWeekHighChangePercent;
    }

    @JsonProperty("fiftyTwoWeekLow")
    public Double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    @JsonProperty("fiftyTwoWeekLow")
    public void setFiftyTwoWeekLow(Double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    @JsonProperty("fiftyTwoWeekHigh")
    public Double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    @JsonProperty("fiftyTwoWeekHigh")
    public void setFiftyTwoWeekHigh(Double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    @JsonProperty("marketState")
    public String getMarketState() {
        return marketState;
    }

    @JsonProperty("marketState")
    public void setMarketState(String marketState) {
        this.marketState = marketState;
    }

    @JsonProperty("underlyingSymbol")
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    @JsonProperty("underlyingSymbol")
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    @JsonProperty("sourceInterval")
    public Long getSourceInterval() {
        return sourceInterval;
    }

    @JsonProperty("sourceInterval")
    public void setSourceInterval(Long sourceInterval) {
        this.sourceInterval = sourceInterval;
    }

    @JsonProperty("exchangeDataDelayedBy")
    public Long getExchangeDataDelayedBy() {
        return exchangeDataDelayedBy;
    }

    @JsonProperty("exchangeDataDelayedBy")
    public void setExchangeDataDelayedBy(Long exchangeDataDelayedBy) {
        this.exchangeDataDelayedBy = exchangeDataDelayedBy;
    }

    @JsonProperty("exchangeTimezoneName")
    public String getExchangeTimezoneName() {
        return exchangeTimezoneName;
    }

    @JsonProperty("exchangeTimezoneName")
    public void setExchangeTimezoneName(String exchangeTimezoneName) {
        this.exchangeTimezoneName = exchangeTimezoneName;
    }

    @JsonProperty("exchangeTimezoneShortName")
    public String getExchangeTimezoneShortName() {
        return exchangeTimezoneShortName;
    }

    @JsonProperty("exchangeTimezoneShortName")
    public void setExchangeTimezoneShortName(String exchangeTimezoneShortName) {
        this.exchangeTimezoneShortName = exchangeTimezoneShortName;
    }

    @JsonProperty("gmtOffSetMilliseconds")
    public Long getGmtOffSetMilliseconds() {
        return gmtOffSetMilliseconds;
    }

    @JsonProperty("gmtOffSetMilliseconds")
    public void setGmtOffSetMilliseconds(Long gmtOffSetMilliseconds) {
        this.gmtOffSetMilliseconds = gmtOffSetMilliseconds;
    }

    @JsonProperty("esgPopulated")
    public Boolean getEsgPopulated() {
        return esgPopulated;
    }

    @JsonProperty("esgPopulated")
    public void setEsgPopulated(Boolean esgPopulated) {
        this.esgPopulated = esgPopulated;
    }

    @JsonProperty("tradeable")
    public Boolean getTradeable() {
        return tradeable;
    }

    @JsonProperty("tradeable")
    public void setTradeable(Boolean tradeable) {
        this.tradeable = tradeable;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Quote.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("region");
        sb.append('=');
        sb.append(((this.region == null)?"<null>":this.region));
        sb.append(',');
        sb.append("quoteType");
        sb.append('=');
        sb.append(((this.quoteType == null)?"<null>":this.quoteType));
        sb.append(',');
        sb.append("triggerable");
        sb.append('=');
        sb.append(((this.triggerable == null)?"<null>":this.triggerable));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("priceHint");
        sb.append('=');
        sb.append(((this.priceHint == null)?"<null>":this.priceHint));
        sb.append(',');
        sb.append("regularMarketChange");
        sb.append('=');
        sb.append(((this.regularMarketChange == null)?"<null>":this.regularMarketChange));
        sb.append(',');
        sb.append("regularMarketChangePercent");
        sb.append('=');
        sb.append(((this.regularMarketChangePercent == null)?"<null>":this.regularMarketChangePercent));
        sb.append(',');
        sb.append("regularMarketTime");
        sb.append('=');
        sb.append(((this.regularMarketTime == null)?"<null>":this.regularMarketTime));
        sb.append(',');
        sb.append("regularMarketPrice");
        sb.append('=');
        sb.append(((this.regularMarketPrice == null)?"<null>":this.regularMarketPrice));
        sb.append(',');
        sb.append("regularMarketDayHigh");
        sb.append('=');
        sb.append(((this.regularMarketDayHigh == null)?"<null>":this.regularMarketDayHigh));
        sb.append(',');
        sb.append("regularMarketDayRange");
        sb.append('=');
        sb.append(((this.regularMarketDayRange == null)?"<null>":this.regularMarketDayRange));
        sb.append(',');
        sb.append("regularMarketDayLow");
        sb.append('=');
        sb.append(((this.regularMarketDayLow == null)?"<null>":this.regularMarketDayLow));
        sb.append(',');
        sb.append("regularMarketVolume");
        sb.append('=');
        sb.append(((this.regularMarketVolume == null)?"<null>":this.regularMarketVolume));
        sb.append(',');
        sb.append("regularMarketPreviousClose");
        sb.append('=');
        sb.append(((this.regularMarketPreviousClose == null)?"<null>":this.regularMarketPreviousClose));
        sb.append(',');
        sb.append("bid");
        sb.append('=');
        sb.append(((this.bid == null)?"<null>":this.bid));
        sb.append(',');
        sb.append("ask");
        sb.append('=');
        sb.append(((this.ask == null)?"<null>":this.ask));
        sb.append(',');
        sb.append("bidSize");
        sb.append('=');
        sb.append(((this.bidSize == null)?"<null>":this.bidSize));
        sb.append(',');
        sb.append("askSize");
        sb.append('=');
        sb.append(((this.askSize == null)?"<null>":this.askSize));
        sb.append(',');
        sb.append("exchange");
        sb.append('=');
        sb.append(((this.exchange == null)?"<null>":this.exchange));
        sb.append(',');
        sb.append("market");
        sb.append('=');
        sb.append(((this.market == null)?"<null>":this.market));
        sb.append(',');
        sb.append("fullExchangeName");
        sb.append('=');
        sb.append(((this.fullExchangeName == null)?"<null>":this.fullExchangeName));
        sb.append(',');
        sb.append("shortName");
        sb.append('=');
        sb.append(((this.shortName == null)?"<null>":this.shortName));
        sb.append(',');
        sb.append("regularMarketOpen");
        sb.append('=');
        sb.append(((this.regularMarketOpen == null)?"<null>":this.regularMarketOpen));
        sb.append(',');
        sb.append("fiftyTwoWeekLowChange");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekLowChange == null)?"<null>":this.fiftyTwoWeekLowChange));
        sb.append(',');
        sb.append("fiftyTwoWeekLowChangePercent");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekLowChangePercent == null)?"<null>":this.fiftyTwoWeekLowChangePercent));
        sb.append(',');
        sb.append("fiftyTwoWeekRange");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekRange == null)?"<null>":this.fiftyTwoWeekRange));
        sb.append(',');
        sb.append("fiftyTwoWeekHighChange");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekHighChange == null)?"<null>":this.fiftyTwoWeekHighChange));
        sb.append(',');
        sb.append("fiftyTwoWeekHighChangePercent");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekHighChangePercent == null)?"<null>":this.fiftyTwoWeekHighChangePercent));
        sb.append(',');
        sb.append("fiftyTwoWeekLow");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekLow == null)?"<null>":this.fiftyTwoWeekLow));
        sb.append(',');
        sb.append("fiftyTwoWeekHigh");
        sb.append('=');
        sb.append(((this.fiftyTwoWeekHigh == null)?"<null>":this.fiftyTwoWeekHigh));
        sb.append(',');
        sb.append("marketState");
        sb.append('=');
        sb.append(((this.marketState == null)?"<null>":this.marketState));
        sb.append(',');
        sb.append("underlyingSymbol");
        sb.append('=');
        sb.append(((this.underlyingSymbol == null)?"<null>":this.underlyingSymbol));
        sb.append(',');
        sb.append("sourceInterval");
        sb.append('=');
        sb.append(((this.sourceInterval == null)?"<null>":this.sourceInterval));
        sb.append(',');
        sb.append("exchangeDataDelayedBy");
        sb.append('=');
        sb.append(((this.exchangeDataDelayedBy == null)?"<null>":this.exchangeDataDelayedBy));
        sb.append(',');
        sb.append("exchangeTimezoneName");
        sb.append('=');
        sb.append(((this.exchangeTimezoneName == null)?"<null>":this.exchangeTimezoneName));
        sb.append(',');
        sb.append("exchangeTimezoneShortName");
        sb.append('=');
        sb.append(((this.exchangeTimezoneShortName == null)?"<null>":this.exchangeTimezoneShortName));
        sb.append(',');
        sb.append("gmtOffSetMilliseconds");
        sb.append('=');
        sb.append(((this.gmtOffSetMilliseconds == null)?"<null>":this.gmtOffSetMilliseconds));
        sb.append(',');
        sb.append("esgPopulated");
        sb.append('=');
        sb.append(((this.esgPopulated == null)?"<null>":this.esgPopulated));
        sb.append(',');
        sb.append("tradeable");
        sb.append('=');
        sb.append(((this.tradeable == null)?"<null>":this.tradeable));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.fullExchangeName == null)? 0 :this.fullExchangeName.hashCode()));
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekLowChangePercent == null)? 0 :this.fiftyTwoWeekLowChangePercent.hashCode()));
        result = ((result* 31)+((this.gmtOffSetMilliseconds == null)? 0 :this.gmtOffSetMilliseconds.hashCode()));
        result = ((result* 31)+((this.regularMarketOpen == null)? 0 :this.regularMarketOpen.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.regularMarketTime == null)? 0 :this.regularMarketTime.hashCode()));
        result = ((result* 31)+((this.regularMarketChangePercent == null)? 0 :this.regularMarketChangePercent.hashCode()));
        result = ((result* 31)+((this.quoteType == null)? 0 :this.quoteType.hashCode()));
        result = ((result* 31)+((this.regularMarketDayRange == null)? 0 :this.regularMarketDayRange.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekLowChange == null)? 0 :this.fiftyTwoWeekLowChange.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekHighChangePercent == null)? 0 :this.fiftyTwoWeekHighChangePercent.hashCode()));
        result = ((result* 31)+((this.underlyingSymbol == null)? 0 :this.underlyingSymbol.hashCode()));
        result = ((result* 31)+((this.regularMarketDayHigh == null)? 0 :this.regularMarketDayHigh.hashCode()));
        result = ((result* 31)+((this.tradeable == null)? 0 :this.tradeable.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.regularMarketPreviousClose == null)? 0 :this.regularMarketPreviousClose.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekHigh == null)? 0 :this.fiftyTwoWeekHigh.hashCode()));
        result = ((result* 31)+((this.askSize == null)? 0 :this.askSize.hashCode()));
        result = ((result* 31)+((this.exchangeTimezoneName == null)? 0 :this.exchangeTimezoneName.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekHighChange == null)? 0 :this.fiftyTwoWeekHighChange.hashCode()));
        result = ((result* 31)+((this.esgPopulated == null)? 0 :this.esgPopulated.hashCode()));
        result = ((result* 31)+((this.regularMarketChange == null)? 0 :this.regularMarketChange.hashCode()));
        result = ((result* 31)+((this.bidSize == null)? 0 :this.bidSize.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekRange == null)? 0 :this.fiftyTwoWeekRange.hashCode()));
        result = ((result* 31)+((this.exchangeDataDelayedBy == null)? 0 :this.exchangeDataDelayedBy.hashCode()));
        result = ((result* 31)+((this.exchangeTimezoneShortName == null)? 0 :this.exchangeTimezoneShortName.hashCode()));
        result = ((result* 31)+((this.regularMarketPrice == null)? 0 :this.regularMarketPrice.hashCode()));
        result = ((result* 31)+((this.fiftyTwoWeekLow == null)? 0 :this.fiftyTwoWeekLow.hashCode()));
        result = ((result* 31)+((this.marketState == null)? 0 :this.marketState.hashCode()));
        result = ((result* 31)+((this.regularMarketVolume == null)? 0 :this.regularMarketVolume.hashCode()));
        result = ((result* 31)+((this.market == null)? 0 :this.market.hashCode()));
        result = ((result* 31)+((this.priceHint == null)? 0 :this.priceHint.hashCode()));
        result = ((result* 31)+((this.ask == null)? 0 :this.ask.hashCode()));
        result = ((result* 31)+((this.regularMarketDayLow == null)? 0 :this.regularMarketDayLow.hashCode()));
        result = ((result* 31)+((this.exchange == null)? 0 :this.exchange.hashCode()));
        result = ((result* 31)+((this.sourceInterval == null)? 0 :this.sourceInterval.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.region == null)? 0 :this.region.hashCode()));
        result = ((result* 31)+((this.bid == null)? 0 :this.bid.hashCode()));
        result = ((result* 31)+((this.shortName == null)? 0 :this.shortName.hashCode()));
        result = ((result* 31)+((this.triggerable == null)? 0 :this.triggerable.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Quote) == false) {
            return false;
        }
        Quote rhs = ((Quote) other);
        return (((((((((((((((((((((((((((((((((((((((((((this.fullExchangeName == rhs.fullExchangeName)||((this.fullExchangeName!= null)&&this.fullExchangeName.equals(rhs.fullExchangeName)))&&((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol))))&&((this.fiftyTwoWeekLowChangePercent == rhs.fiftyTwoWeekLowChangePercent)||((this.fiftyTwoWeekLowChangePercent!= null)&&this.fiftyTwoWeekLowChangePercent.equals(rhs.fiftyTwoWeekLowChangePercent))))&&((this.gmtOffSetMilliseconds == rhs.gmtOffSetMilliseconds)||((this.gmtOffSetMilliseconds!= null)&&this.gmtOffSetMilliseconds.equals(rhs.gmtOffSetMilliseconds))))&&((this.regularMarketOpen == rhs.regularMarketOpen)||((this.regularMarketOpen!= null)&&this.regularMarketOpen.equals(rhs.regularMarketOpen))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.regularMarketTime == rhs.regularMarketTime)||((this.regularMarketTime!= null)&&this.regularMarketTime.equals(rhs.regularMarketTime))))&&((this.regularMarketChangePercent == rhs.regularMarketChangePercent)||((this.regularMarketChangePercent!= null)&&this.regularMarketChangePercent.equals(rhs.regularMarketChangePercent))))&&((this.quoteType == rhs.quoteType)||((this.quoteType!= null)&&this.quoteType.equals(rhs.quoteType))))&&((this.regularMarketDayRange == rhs.regularMarketDayRange)||((this.regularMarketDayRange!= null)&&this.regularMarketDayRange.equals(rhs.regularMarketDayRange))))&&((this.fiftyTwoWeekLowChange == rhs.fiftyTwoWeekLowChange)||((this.fiftyTwoWeekLowChange!= null)&&this.fiftyTwoWeekLowChange.equals(rhs.fiftyTwoWeekLowChange))))&&((this.fiftyTwoWeekHighChangePercent == rhs.fiftyTwoWeekHighChangePercent)||((this.fiftyTwoWeekHighChangePercent!= null)&&this.fiftyTwoWeekHighChangePercent.equals(rhs.fiftyTwoWeekHighChangePercent))))&&((this.underlyingSymbol == rhs.underlyingSymbol)||((this.underlyingSymbol!= null)&&this.underlyingSymbol.equals(rhs.underlyingSymbol))))&&((this.regularMarketDayHigh == rhs.regularMarketDayHigh)||((this.regularMarketDayHigh!= null)&&this.regularMarketDayHigh.equals(rhs.regularMarketDayHigh))))&&((this.tradeable == rhs.tradeable)||((this.tradeable!= null)&&this.tradeable.equals(rhs.tradeable))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.regularMarketPreviousClose == rhs.regularMarketPreviousClose)||((this.regularMarketPreviousClose!= null)&&this.regularMarketPreviousClose.equals(rhs.regularMarketPreviousClose))))&&((this.fiftyTwoWeekHigh == rhs.fiftyTwoWeekHigh)||((this.fiftyTwoWeekHigh!= null)&&this.fiftyTwoWeekHigh.equals(rhs.fiftyTwoWeekHigh))))&&((this.askSize == rhs.askSize)||((this.askSize!= null)&&this.askSize.equals(rhs.askSize))))&&((this.exchangeTimezoneName == rhs.exchangeTimezoneName)||((this.exchangeTimezoneName!= null)&&this.exchangeTimezoneName.equals(rhs.exchangeTimezoneName))))&&((this.fiftyTwoWeekHighChange == rhs.fiftyTwoWeekHighChange)||((this.fiftyTwoWeekHighChange!= null)&&this.fiftyTwoWeekHighChange.equals(rhs.fiftyTwoWeekHighChange))))&&((this.esgPopulated == rhs.esgPopulated)||((this.esgPopulated!= null)&&this.esgPopulated.equals(rhs.esgPopulated))))&&((this.regularMarketChange == rhs.regularMarketChange)||((this.regularMarketChange!= null)&&this.regularMarketChange.equals(rhs.regularMarketChange))))&&((this.bidSize == rhs.bidSize)||((this.bidSize!= null)&&this.bidSize.equals(rhs.bidSize))))&&((this.fiftyTwoWeekRange == rhs.fiftyTwoWeekRange)||((this.fiftyTwoWeekRange!= null)&&this.fiftyTwoWeekRange.equals(rhs.fiftyTwoWeekRange))))&&((this.exchangeDataDelayedBy == rhs.exchangeDataDelayedBy)||((this.exchangeDataDelayedBy!= null)&&this.exchangeDataDelayedBy.equals(rhs.exchangeDataDelayedBy))))&&((this.exchangeTimezoneShortName == rhs.exchangeTimezoneShortName)||((this.exchangeTimezoneShortName!= null)&&this.exchangeTimezoneShortName.equals(rhs.exchangeTimezoneShortName))))&&((this.regularMarketPrice == rhs.regularMarketPrice)||((this.regularMarketPrice!= null)&&this.regularMarketPrice.equals(rhs.regularMarketPrice))))&&((this.fiftyTwoWeekLow == rhs.fiftyTwoWeekLow)||((this.fiftyTwoWeekLow!= null)&&this.fiftyTwoWeekLow.equals(rhs.fiftyTwoWeekLow))))&&((this.marketState == rhs.marketState)||((this.marketState!= null)&&this.marketState.equals(rhs.marketState))))&&((this.regularMarketVolume == rhs.regularMarketVolume)||((this.regularMarketVolume!= null)&&this.regularMarketVolume.equals(rhs.regularMarketVolume))))&&((this.market == rhs.market)||((this.market!= null)&&this.market.equals(rhs.market))))&&((this.priceHint == rhs.priceHint)||((this.priceHint!= null)&&this.priceHint.equals(rhs.priceHint))))&&((this.ask == rhs.ask)||((this.ask!= null)&&this.ask.equals(rhs.ask))))&&((this.regularMarketDayLow == rhs.regularMarketDayLow)||((this.regularMarketDayLow!= null)&&this.regularMarketDayLow.equals(rhs.regularMarketDayLow))))&&((this.exchange == rhs.exchange)||((this.exchange!= null)&&this.exchange.equals(rhs.exchange))))&&((this.sourceInterval == rhs.sourceInterval)||((this.sourceInterval!= null)&&this.sourceInterval.equals(rhs.sourceInterval))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.region == rhs.region)||((this.region!= null)&&this.region.equals(rhs.region))))&&((this.bid == rhs.bid)||((this.bid!= null)&&this.bid.equals(rhs.bid))))&&((this.shortName == rhs.shortName)||((this.shortName!= null)&&this.shortName.equals(rhs.shortName))))&&((this.triggerable == rhs.triggerable)||((this.triggerable!= null)&&this.triggerable.equals(rhs.triggerable))));
    }

}

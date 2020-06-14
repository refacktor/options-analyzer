
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
    "contractSymbol",
    "strike",
    "currency",
    "lastPrice",
    "change",
    "percentChange",
    "volume",
    "openInterest",
    "bid",
    "ask",
    "contractSize",
    "expiration",
    "lastTradeDate",
    "impliedVolatility",
    "inTheMoney"
})
public class Call {

    @JsonProperty("contractSymbol")
    private String contractSymbol;
    @JsonProperty("strike")
    private Double strike;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("lastPrice")
    private Double lastPrice;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("percentChange")
    private Double percentChange;
    @JsonProperty("volume")
    private Long volume;
    @JsonProperty("openInterest")
    private Long openInterest;
    @JsonProperty("bid")
    private Double bid;
    @JsonProperty("ask")
    private Double ask;
    @JsonProperty("contractSize")
    private String contractSize;
    @JsonProperty("expiration")
    private Long expiration;
    @JsonProperty("lastTradeDate")
    private Long lastTradeDate;
    @JsonProperty("impliedVolatility")
    private Double impliedVolatility;
    @JsonProperty("inTheMoney")
    private Boolean inTheMoney;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contractSymbol")
    public String getContractSymbol() {
        return contractSymbol;
    }

    @JsonProperty("contractSymbol")
    public void setContractSymbol(String contractSymbol) {
        this.contractSymbol = contractSymbol;
    }

    @JsonProperty("strike")
    public Double getStrike() {
        return strike;
    }

    @JsonProperty("strike")
    public void setStrike(Double strike) {
        this.strike = strike;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("lastPrice")
    public Double getLastPrice() {
        return lastPrice;
    }

    @JsonProperty("lastPrice")
    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("percentChange")
    public Double getPercentChange() {
        return percentChange;
    }

    @JsonProperty("percentChange")
    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    @JsonProperty("volume")
    public Long getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @JsonProperty("openInterest")
    public Long getOpenInterest() {
        return openInterest;
    }

    @JsonProperty("openInterest")
    public void setOpenInterest(Long openInterest) {
        this.openInterest = openInterest;
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

    @JsonProperty("contractSize")
    public String getContractSize() {
        return contractSize;
    }

    @JsonProperty("contractSize")
    public void setContractSize(String contractSize) {
        this.contractSize = contractSize;
    }

    @JsonProperty("expiration")
    public Long getExpiration() {
        return expiration;
    }

    @JsonProperty("expiration")
    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    @JsonProperty("lastTradeDate")
    public Long getLastTradeDate() {
        return lastTradeDate;
    }

    @JsonProperty("lastTradeDate")
    public void setLastTradeDate(Long lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    @JsonProperty("impliedVolatility")
    public Double getImpliedVolatility() {
        return impliedVolatility;
    }

    @JsonProperty("impliedVolatility")
    public void setImpliedVolatility(Double impliedVolatility) {
        this.impliedVolatility = impliedVolatility;
    }

    @JsonProperty("inTheMoney")
    public Boolean getInTheMoney() {
        return inTheMoney;
    }

    @JsonProperty("inTheMoney")
    public void setInTheMoney(Boolean inTheMoney) {
        this.inTheMoney = inTheMoney;
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
        sb.append(Call.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("contractSymbol");
        sb.append('=');
        sb.append(((this.contractSymbol == null)?"<null>":this.contractSymbol));
        sb.append(',');
        sb.append("strike");
        sb.append('=');
        sb.append(((this.strike == null)?"<null>":this.strike));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("lastPrice");
        sb.append('=');
        sb.append(((this.lastPrice == null)?"<null>":this.lastPrice));
        sb.append(',');
        sb.append("change");
        sb.append('=');
        sb.append(((this.change == null)?"<null>":this.change));
        sb.append(',');
        sb.append("percentChange");
        sb.append('=');
        sb.append(((this.percentChange == null)?"<null>":this.percentChange));
        sb.append(',');
        sb.append("volume");
        sb.append('=');
        sb.append(((this.volume == null)?"<null>":this.volume));
        sb.append(',');
        sb.append("openInterest");
        sb.append('=');
        sb.append(((this.openInterest == null)?"<null>":this.openInterest));
        sb.append(',');
        sb.append("bid");
        sb.append('=');
        sb.append(((this.bid == null)?"<null>":this.bid));
        sb.append(',');
        sb.append("ask");
        sb.append('=');
        sb.append(((this.ask == null)?"<null>":this.ask));
        sb.append(',');
        sb.append("contractSize");
        sb.append('=');
        sb.append(((this.contractSize == null)?"<null>":this.contractSize));
        sb.append(',');
        sb.append("expiration");
        sb.append('=');
        sb.append(((this.expiration == null)?"<null>":this.expiration));
        sb.append(',');
        sb.append("lastTradeDate");
        sb.append('=');
        sb.append(((this.lastTradeDate == null)?"<null>":this.lastTradeDate));
        sb.append(',');
        sb.append("impliedVolatility");
        sb.append('=');
        sb.append(((this.impliedVolatility == null)?"<null>":this.impliedVolatility));
        sb.append(',');
        sb.append("inTheMoney");
        sb.append('=');
        sb.append(((this.inTheMoney == null)?"<null>":this.inTheMoney));
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
        result = ((result* 31)+((this.percentChange == null)? 0 :this.percentChange.hashCode()));
        result = ((result* 31)+((this.openInterest == null)? 0 :this.openInterest.hashCode()));
        result = ((result* 31)+((this.strike == null)? 0 :this.strike.hashCode()));
        result = ((result* 31)+((this.change == null)? 0 :this.change.hashCode()));
        result = ((result* 31)+((this.inTheMoney == null)? 0 :this.inTheMoney.hashCode()));
        result = ((result* 31)+((this.impliedVolatility == null)? 0 :this.impliedVolatility.hashCode()));
        result = ((result* 31)+((this.volume == null)? 0 :this.volume.hashCode()));
        result = ((result* 31)+((this.contractSymbol == null)? 0 :this.contractSymbol.hashCode()));
        result = ((result* 31)+((this.ask == null)? 0 :this.ask.hashCode()));
        result = ((result* 31)+((this.lastTradeDate == null)? 0 :this.lastTradeDate.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.contractSize == null)? 0 :this.contractSize.hashCode()));
        result = ((result* 31)+((this.expiration == null)? 0 :this.expiration.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.bid == null)? 0 :this.bid.hashCode()));
        result = ((result* 31)+((this.lastPrice == null)? 0 :this.lastPrice.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Call) == false) {
            return false;
        }
        Call rhs = ((Call) other);
        return (((((((((((((((((this.percentChange == rhs.percentChange)||((this.percentChange!= null)&&this.percentChange.equals(rhs.percentChange)))&&((this.openInterest == rhs.openInterest)||((this.openInterest!= null)&&this.openInterest.equals(rhs.openInterest))))&&((this.strike == rhs.strike)||((this.strike!= null)&&this.strike.equals(rhs.strike))))&&((this.change == rhs.change)||((this.change!= null)&&this.change.equals(rhs.change))))&&((this.inTheMoney == rhs.inTheMoney)||((this.inTheMoney!= null)&&this.inTheMoney.equals(rhs.inTheMoney))))&&((this.impliedVolatility == rhs.impliedVolatility)||((this.impliedVolatility!= null)&&this.impliedVolatility.equals(rhs.impliedVolatility))))&&((this.volume == rhs.volume)||((this.volume!= null)&&this.volume.equals(rhs.volume))))&&((this.contractSymbol == rhs.contractSymbol)||((this.contractSymbol!= null)&&this.contractSymbol.equals(rhs.contractSymbol))))&&((this.ask == rhs.ask)||((this.ask!= null)&&this.ask.equals(rhs.ask))))&&((this.lastTradeDate == rhs.lastTradeDate)||((this.lastTradeDate!= null)&&this.lastTradeDate.equals(rhs.lastTradeDate))))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.contractSize == rhs.contractSize)||((this.contractSize!= null)&&this.contractSize.equals(rhs.contractSize))))&&((this.expiration == rhs.expiration)||((this.expiration!= null)&&this.expiration.equals(rhs.expiration))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.bid == rhs.bid)||((this.bid!= null)&&this.bid.equals(rhs.bid))))&&((this.lastPrice == rhs.lastPrice)||((this.lastPrice!= null)&&this.lastPrice.equals(rhs.lastPrice))));
    }

}

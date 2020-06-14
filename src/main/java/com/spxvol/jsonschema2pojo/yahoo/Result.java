
package com.spxvol.jsonschema2pojo.yahoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "underlyingSymbol",
    "expirationDates",
    "strikes",
    "hasMiniOptions",
    "quote",
    "options"
})
public class Result {

    @JsonProperty("underlyingSymbol")
    private String underlyingSymbol;
    @JsonProperty("expirationDates")
    private List<Long> expirationDates = new ArrayList<Long>();
    @JsonProperty("strikes")
    private List<Double> strikes = new ArrayList<Double>();
    @JsonProperty("hasMiniOptions")
    private Boolean hasMiniOptions;
    @JsonProperty("quote")
    private Quote quote;
    @JsonProperty("options")
    private List<Option> options = new ArrayList<Option>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("underlyingSymbol")
    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    @JsonProperty("underlyingSymbol")
    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    @JsonProperty("expirationDates")
    public List<Long> getExpirationDates() {
        return expirationDates;
    }

    @JsonProperty("expirationDates")
    public void setExpirationDates(List<Long> expirationDates) {
        this.expirationDates = expirationDates;
    }

    @JsonProperty("strikes")
    public List<Double> getStrikes() {
        return strikes;
    }

    @JsonProperty("strikes")
    public void setStrikes(List<Double> strikes) {
        this.strikes = strikes;
    }

    @JsonProperty("hasMiniOptions")
    public Boolean getHasMiniOptions() {
        return hasMiniOptions;
    }

    @JsonProperty("hasMiniOptions")
    public void setHasMiniOptions(Boolean hasMiniOptions) {
        this.hasMiniOptions = hasMiniOptions;
    }

    @JsonProperty("quote")
    public Quote getQuote() {
        return quote;
    }

    @JsonProperty("quote")
    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @JsonProperty("options")
    public List<Option> getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(List<Option> options) {
        this.options = options;
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
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("underlyingSymbol");
        sb.append('=');
        sb.append(((this.underlyingSymbol == null)?"<null>":this.underlyingSymbol));
        sb.append(',');
        sb.append("expirationDates");
        sb.append('=');
        sb.append(((this.expirationDates == null)?"<null>":this.expirationDates));
        sb.append(',');
        sb.append("strikes");
        sb.append('=');
        sb.append(((this.strikes == null)?"<null>":this.strikes));
        sb.append(',');
        sb.append("hasMiniOptions");
        sb.append('=');
        sb.append(((this.hasMiniOptions == null)?"<null>":this.hasMiniOptions));
        sb.append(',');
        sb.append("quote");
        sb.append('=');
        sb.append(((this.quote == null)?"<null>":this.quote));
        sb.append(',');
        sb.append("options");
        sb.append('=');
        sb.append(((this.options == null)?"<null>":this.options));
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
        result = ((result* 31)+((this.underlyingSymbol == null)? 0 :this.underlyingSymbol.hashCode()));
        result = ((result* 31)+((this.expirationDates == null)? 0 :this.expirationDates.hashCode()));
        result = ((result* 31)+((this.quote == null)? 0 :this.quote.hashCode()));
        result = ((result* 31)+((this.hasMiniOptions == null)? 0 :this.hasMiniOptions.hashCode()));
        result = ((result* 31)+((this.strikes == null)? 0 :this.strikes.hashCode()));
        result = ((result* 31)+((this.options == null)? 0 :this.options.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return ((((((((this.underlyingSymbol == rhs.underlyingSymbol)||((this.underlyingSymbol!= null)&&this.underlyingSymbol.equals(rhs.underlyingSymbol)))&&((this.expirationDates == rhs.expirationDates)||((this.expirationDates!= null)&&this.expirationDates.equals(rhs.expirationDates))))&&((this.quote == rhs.quote)||((this.quote!= null)&&this.quote.equals(rhs.quote))))&&((this.hasMiniOptions == rhs.hasMiniOptions)||((this.hasMiniOptions!= null)&&this.hasMiniOptions.equals(rhs.hasMiniOptions))))&&((this.strikes == rhs.strikes)||((this.strikes!= null)&&this.strikes.equals(rhs.strikes))))&&((this.options == rhs.options)||((this.options!= null)&&this.options.equals(rhs.options))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

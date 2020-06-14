
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
    "expirationDate",
    "hasMiniOptions",
    "calls",
    "puts"
})
public class Option {

    @JsonProperty("expirationDate")
    private Long expirationDate;
    @JsonProperty("hasMiniOptions")
    private Boolean hasMiniOptions;
    @JsonProperty("calls")
    private List<Call> calls = new ArrayList<Call>();
    @JsonProperty("puts")
    private List<Put> puts = new ArrayList<Put>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("expirationDate")
    public Long getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("hasMiniOptions")
    public Boolean getHasMiniOptions() {
        return hasMiniOptions;
    }

    @JsonProperty("hasMiniOptions")
    public void setHasMiniOptions(Boolean hasMiniOptions) {
        this.hasMiniOptions = hasMiniOptions;
    }

    @JsonProperty("calls")
    public List<Call> getCalls() {
        return calls;
    }

    @JsonProperty("calls")
    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    @JsonProperty("puts")
    public List<Put> getPuts() {
        return puts;
    }

    @JsonProperty("puts")
    public void setPuts(List<Put> puts) {
        this.puts = puts;
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
        sb.append(Option.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("expirationDate");
        sb.append('=');
        sb.append(((this.expirationDate == null)?"<null>":this.expirationDate));
        sb.append(',');
        sb.append("hasMiniOptions");
        sb.append('=');
        sb.append(((this.hasMiniOptions == null)?"<null>":this.hasMiniOptions));
        sb.append(',');
        sb.append("calls");
        sb.append('=');
        sb.append(((this.calls == null)?"<null>":this.calls));
        sb.append(',');
        sb.append("puts");
        sb.append('=');
        sb.append(((this.puts == null)?"<null>":this.puts));
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
        result = ((result* 31)+((this.puts == null)? 0 :this.puts.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.hasMiniOptions == null)? 0 :this.hasMiniOptions.hashCode()));
        result = ((result* 31)+((this.calls == null)? 0 :this.calls.hashCode()));
        result = ((result* 31)+((this.expirationDate == null)? 0 :this.expirationDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Option) == false) {
            return false;
        }
        Option rhs = ((Option) other);
        return ((((((this.puts == rhs.puts)||((this.puts!= null)&&this.puts.equals(rhs.puts)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.hasMiniOptions == rhs.hasMiniOptions)||((this.hasMiniOptions!= null)&&this.hasMiniOptions.equals(rhs.hasMiniOptions))))&&((this.calls == rhs.calls)||((this.calls!= null)&&this.calls.equals(rhs.calls))))&&((this.expirationDate == rhs.expirationDate)||((this.expirationDate!= null)&&this.expirationDate.equals(rhs.expirationDate))));
    }

}

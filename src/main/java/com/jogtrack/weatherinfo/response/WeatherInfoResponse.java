
package com.jogtrack.weatherinfo.response;

import java.math.BigDecimal;
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
    "latitude",
    "longitude",
    "timezone",
    "currently",
    "offset"
})
public class WeatherInfoResponse {

    @JsonProperty("latitude")
    private BigDecimal latitude;
    @JsonProperty("longitude")
    private BigDecimal longitude;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("currently")
    private Currently currently;
    @JsonProperty("offset")
    private Integer offset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("latitude")
    public BigDecimal getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public BigDecimal getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("currently")
    public Currently getCurrently() {
        return currently;
    }

    @JsonProperty("currently")
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

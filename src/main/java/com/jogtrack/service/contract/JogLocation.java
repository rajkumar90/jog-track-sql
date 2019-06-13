package com.jogtrack.service.contract;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogLocation {
	@JsonProperty
	private BigDecimal latitude;
	
	@JsonProperty
	private BigDecimal longitude;

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
}

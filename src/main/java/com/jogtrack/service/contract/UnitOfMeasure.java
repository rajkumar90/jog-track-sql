package com.jogtrack.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This enum holds the different units of measure
 * (For eg. KM for distance, Pascal for pressure, etc)
 * @author raj
 *
 */
public enum UnitOfMeasure {
	// Kilometer
	@JsonProperty("KM")
	KM,
	// Minute
	@JsonProperty("MIN")
	MIN,
	// HectoPascal
	@JsonProperty("HPA")
	HPA,
	// Meters per Second
	@JsonProperty("MPS")
	MPS,
	// Kilometers per Hour
	@JsonProperty("KMPH")
	KMPH,
	// Percent
	@JsonProperty("PERCENT")
	PERCENT,
	// Celsius
	@JsonProperty("C")
	C,
	// No Unit - For parameters like Relative Humidity
	@JsonProperty("NA")
	NA;
	
}

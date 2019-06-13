package com.jogtrack.service.contract;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Measurement {
	@JsonProperty
	private BigDecimal measurementValue;
	
	@JsonProperty
	private UnitOfMeasure unitOfMeasure;
	
	public Measurement(BigDecimal measurementValue, UnitOfMeasure unitOfMeasure) {
		super();
		this.measurementValue = measurementValue;
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public Measurement() {
		
	}
	
	public BigDecimal getMeasurementValue() {
		return measurementValue;
	}

	public void setMeasurementValue(BigDecimal measurementValue) {
		this.measurementValue = measurementValue;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
	
}

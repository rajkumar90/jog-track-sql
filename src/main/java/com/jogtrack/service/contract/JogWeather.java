package com.jogtrack.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogWeather {
	@JsonProperty
	private Measurement temperature;
	
	@JsonProperty
	private Measurement pressure;
	
	@JsonProperty
	private Measurement humidity;
	
	@JsonProperty
	private Measurement windSpeed;

	public Measurement getTemperature() {
		return temperature;
	}

	public void setTemperature(Measurement temperature) {
		this.temperature = temperature;
	}

	public Measurement getPressure() {
		return pressure;
	}

	public void setPressure(Measurement pressure) {
		this.pressure = pressure;
	}

	public Measurement getHumidity() {
		return humidity;
	}

	public void setHumidity(Measurement humidity) {
		this.humidity = humidity;
	}

	public Measurement getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Measurement windSpeed) {
		this.windSpeed = windSpeed;
	}

}

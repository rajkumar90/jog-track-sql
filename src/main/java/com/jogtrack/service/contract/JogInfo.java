package com.jogtrack.service.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jogtrack.util.JsonDateDeserializer;

public class JogInfo extends BaseDTO {
	@JsonProperty
	private String jogId;
	
	@JsonProperty
	private String userId;
	
	@JsonProperty
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date jogDate;
	
	@JsonProperty
	private Measurement jogDistance;
	
	// Jog Duration
	@JsonProperty
	private Measurement jogTime;
	
	@JsonProperty
	private JogWeather jogWeather;	
	
	@JsonProperty
	private JogLocation jogLocation;
	
	public Measurement getJogTime() {
		return jogTime;
	}
	public void setJogTime(Measurement jogTime) {
		this.jogTime = jogTime;
	}
	@JsonProperty
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getJogDate() {
		return jogDate;
	}
	public void setJogDate(Date jogDate) {
		this.jogDate = jogDate;
	}
	public Measurement getJogDistance() {
		return jogDistance;
	}
	public void setJogDistance(Measurement jogDistance) {
		this.jogDistance = jogDistance;
	}
	public JogLocation getJogLocation() {
		return jogLocation;
	}
	public void setJogLocation(JogLocation jogLocation) {
		this.jogLocation = jogLocation;
	}
	
	public JogWeather getJogWeather() {
		return jogWeather;
	}
	public void setJogWeather(JogWeather jogWeather) {
		this.jogWeather = jogWeather;
	}
	
	public String getJogId() {
		return jogId;
	}
	public void setJogId(String jogId) {
		this.jogId = jogId;
	}
}

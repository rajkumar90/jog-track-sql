package com.jogtrack.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "JOG_INFO")
public class JogInfoDO extends BaseDO {	

	@Id
	@Column(name = "JOG_ID")
	private String jogId;

	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "JOG_DISTANCE")
	private BigDecimal jogDistance;
	
	@Column(name = "JOG_TIME")
	private BigDecimal jogTime;

	@Column(name = "JOG_DATE")
	private Date jogDate;
	
	@Column(name = "JOG_LOCATION_LAT")
	private BigDecimal jogLocationLat;
	
	@Column(name = "JOG_LOCATION_LONG")
	private BigDecimal jogLocationLong;
	
	@Column(name = "TEMPERATURE")
	private BigDecimal temperature;
	
	@Column(name = "PRESSURE")
	private BigDecimal pressure;
	
	@Column(name = "HUMIDITY")
	private BigDecimal humidity;
	
	@Column(name = "WIND_SPEED")
	private BigDecimal windSpeed;

	@PrePersist
	public void onCreateUserDO() {
		this.jogId = UUID.randomUUID().toString();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getJogId() {
		return jogId;
	}

	public void setJogId(String jogId) {
		this.jogId = jogId;
	}

	public BigDecimal getJogLocationLat() {
		return jogLocationLat;
	}

	public void setJogLocationLat(BigDecimal jogLocationLat) {
		this.jogLocationLat = jogLocationLat;
	}

	public BigDecimal getJogLocationLong() {
		return jogLocationLong;
	}

	public void setJogLocationLong(BigDecimal jogLocationLong) {
		this.jogLocationLong = jogLocationLong;
	}

	public BigDecimal getJogDistance() {
		return jogDistance;
	}

	public void setJogDistance(BigDecimal jogDistance) {
		this.jogDistance = jogDistance;
	}

	public Date getJogDate() {
		return jogDate;
	}

	public void setJogDate(Date jogDate) {
		this.jogDate = jogDate;
	}
	
	public BigDecimal getJogTime() {
		return jogTime;
	}

	public void setJogTime(BigDecimal jogTime) {
		this.jogTime = jogTime;
	}
	
}

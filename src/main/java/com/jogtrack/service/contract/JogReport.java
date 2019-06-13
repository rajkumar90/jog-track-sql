package com.jogtrack.service.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogReport {
	// Weekly/Monthly/Quarterly
	@JsonProperty
	private String reportType;
	
	@JsonProperty
	private String userId;
	
	@JsonProperty
	private Long numJogs;
	
	public Long getNumJogs() {
		return numJogs;
	}

	public void setNumJogs(Long numJogs) {
		this.numJogs = numJogs;
	}

	@JsonProperty
	private Measurement averageDistance;
	
	@JsonProperty
	private Measurement averageSpeed;

	@Override
	public String toString() {
		return "JogReport [reportType=" + reportType + ", userId=" + userId + ", numJogs=" + numJogs
				+ ", averageDistance=" + averageDistance + ", averageSpeed=" + averageSpeed + "]";
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Measurement getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(Measurement averageDistance) {
		this.averageDistance = averageDistance;
	}

	public Measurement getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Measurement averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
}

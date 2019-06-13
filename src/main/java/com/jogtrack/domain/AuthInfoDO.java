package com.jogtrack.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_TOKEN_INFO")
public class AuthInfoDO extends BaseDO {
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "AUTH_TOKEN")
	private String authToken;
	
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expirationDate) {
		this.expiryDate = expirationDate;
	}
	
	
}

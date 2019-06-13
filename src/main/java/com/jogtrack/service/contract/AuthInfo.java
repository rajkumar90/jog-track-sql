package com.jogtrack.service.contract;

import java.util.Date;

public class AuthInfo extends BaseDTO {
	private String userId;
	
	private String authToken;
	
	private Date expiryDate;
	
	public String getUserId() {
		return userId;
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


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + "]";
	}

}

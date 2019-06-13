package com.jogtrack.service.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseDTO {
	@JsonIgnore
	private Date createTs;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTs == null) ? 0 : createTs.hashCode());
		result = prime * result + ((createUserId == null) ? 0 : createUserId.hashCode());
		result = prime * result + ((modifyTs == null) ? 0 : modifyTs.hashCode());
		result = prime * result + ((modifyUserId == null) ? 0 : modifyUserId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDTO other = (BaseDTO) obj;
		if (createTs == null) {
			if (other.createTs != null)
				return false;
		} else if (!createTs.equals(other.createTs))
			return false;
		if (createUserId == null) {
			if (other.createUserId != null)
				return false;
		} else if (!createUserId.equals(other.createUserId))
			return false;
		if (modifyTs == null) {
			if (other.modifyTs != null)
				return false;
		} else if (!modifyTs.equals(other.modifyTs))
			return false;
		if (modifyUserId == null) {
			if (other.modifyUserId != null)
				return false;
		} else if (!modifyUserId.equals(other.modifyUserId))
			return false;
		return true;
	}
	@JsonIgnore
	private Date modifyTs;
	
	@JsonIgnore
	private String createUserId;
	
	@JsonIgnore
	private String modifyUserId;
	
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public Date getModifyTs() {
		return modifyTs;
	}
	public void setModifyTs(Date modifyTs) {
		this.modifyTs = modifyTs;
	}
}

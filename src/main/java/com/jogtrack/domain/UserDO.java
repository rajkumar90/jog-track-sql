package com.jogtrack.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserDO extends BaseDO {
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	private Set<JogInfoDO> jogInfoList;
	
	
	public Set<JogInfoDO> getJogInfoList() {
		return jogInfoList;
	}

	public void setJogInfoList(Set<JogInfoDO> jogInfoList) {
		this.jogInfoList = jogInfoList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

package com.jogtrack.domain.mapper;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.AuthInfoDO;
import com.jogtrack.service.contract.AuthInfo;

@Component
public class AuthInfoDomainMapper {
	public AuthInfoDO mapToDO(AuthInfo authInfo) {
		AuthInfoDO authInfoDO = new AuthInfoDO();
		authInfoDO.setUserId(authInfo.getUserId());
		authInfoDO.setAuthToken(authInfo.getAuthToken());
		authInfoDO.setExpiryDate(authInfo.getExpiryDate());
		
		authInfoDO.setCreateTs(authInfo.getCreateTs());
		authInfoDO.setCreateUserId(authInfo.getCreateUserId());
		authInfoDO.setModifyTs(authInfo.getModifyTs());
		authInfoDO.setModifyUserId(authInfo.getModifyUserId());
		return authInfoDO;
	}
	
	public AuthInfo mapToModel(AuthInfoDO authInfoDO) {
		AuthInfo authInfo = new AuthInfo();
		authInfo.setAuthToken(authInfoDO.getAuthToken());
		authInfo.setUserId(authInfoDO.getUserId());
		authInfo.setExpiryDate(authInfoDO.getExpiryDate());
		
		authInfo.setCreateTs(authInfoDO.getCreateTs());
		authInfo.setCreateUserId(authInfoDO.getCreateUserId());
		authInfo.setModifyTs(authInfoDO.getModifyTs());
		authInfo.setModifyUserId(authInfoDO.getModifyUserId());
		return authInfo;
	}
}

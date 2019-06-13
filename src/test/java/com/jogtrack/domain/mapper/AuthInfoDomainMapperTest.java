package com.jogtrack.domain.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.jogtrack.domain.AuthInfoDO;
import com.jogtrack.service.contract.AuthInfo;

public class AuthInfoDomainMapperTest {
	
	AuthInfoDomainMapper authInfoDomainMapper = new AuthInfoDomainMapper();
	
	@Test
	public void shouldMapToModel() {
		String userId = "regular1", authToken = "abc";
		Date date = new Date();
		AuthInfoDO authInfoDO = new AuthInfoDO();
		authInfoDO.setAuthToken(authToken);
		authInfoDO.setUserId(userId);
		authInfoDO.setExpiryDate(date);
		
		AuthInfo authInfo = authInfoDomainMapper.mapToModel(authInfoDO);
		assertEquals(userId, authInfo.getUserId());
		assertEquals(authToken, authInfo.getAuthToken());
		assertEquals(date, authInfo.getExpiryDate());
	}
	
	@Test
	public void shouldMapToDO() {
		String userId = "regular1", authToken = "abc";
		Date date = new Date();
		
		AuthInfo authInfo = new AuthInfo();
		authInfo.setAuthToken(authToken);
		authInfo.setUserId(userId);
		authInfo.setExpiryDate(date);
		
		AuthInfoDO authInfoDO = authInfoDomainMapper.mapToDO(authInfo);
		
		assertEquals(userId, authInfoDO.getUserId());
		assertEquals(authToken, authInfoDO.getAuthToken());
		assertEquals(date, authInfoDO.getExpiryDate());
	}
}

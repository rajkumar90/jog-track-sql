package com.jogtrack.domain.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jogtrack.domain.UserDO;
import com.jogtrack.service.contract.Role;
import com.jogtrack.service.contract.User;


public class UserDomainMapperTest {
	
	private UserDomainMapper userDomainMapper = new UserDomainMapper();
	
	@Test
	public void shouldMapToModel() {
		String userId = "regular1", password = "abc", role = "REGULAR";
		Date date = new Date();
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		userDO.setPassword(password);
		userDO.setRole(role);
		userDO.setCreateTs(date);
		userDO.setModifyTs(date);
		userDO.setCreateUserId(userId);
		userDO.setModifyUserId(userId);;
		
		User user = userDomainMapper.mapToModel(userDO);
		assertEquals(userId, user.getUserId());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole().name());
		assertEquals(date, user.getCreateTs());
		assertEquals(date, user.getModifyTs());
		assertEquals(userId, user.getCreateUserId());
		assertEquals(userId, user.getModifyUserId());
		
	}
	
	@Test
	public void shouldMapToDO() {
		String userId = "regular1", password = "abc", role = "REGULAR";
		Date date = new Date();
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setRole(Role.valueOf(role));
		user.setCreateTs(date);
		user.setModifyTs(date);
		user.setCreateUserId(userId);
		user.setModifyUserId(userId);
		
		UserDO userDO = userDomainMapper.mapToDO(user);
		assertEquals(userId, userDO.getUserId());
		assertEquals(password, userDO.getPassword());
		assertEquals(role, userDO.getRole());
		assertEquals(date, userDO.getCreateTs());
		assertEquals(date, userDO.getModifyTs());
		assertEquals(userId, userDO.getCreateUserId());
		assertEquals(userId, userDO.getModifyUserId());		
	}
	
	@Test
	public void shouldMapToModelList() {
		List<UserDO> userDOList = new ArrayList<UserDO>();
		String userId = "regular1", password = "abc", role = "REGULAR";
		Date date = new Date();
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		userDO.setPassword(password);
		userDO.setRole(role);
		userDO.setCreateTs(date);
		userDO.setModifyTs(date);
		userDO.setCreateUserId(userId);
		userDO.setModifyUserId(userId);;
		
		for (int i = 0; i < 10; i++)
			userDOList.add(userDO);
		
		List<User> userList = userDomainMapper.mapToModel(userDOList);
		for (int i = 0; i < 10; i++) {
			User user = userList.get(i);
			assertEquals(userId, user.getUserId());
			assertEquals(password, user.getPassword());
			assertEquals(role, user.getRole().name());
			assertEquals(date, user.getCreateTs());
			assertEquals(date, user.getModifyTs());
			assertEquals(userId, user.getCreateUserId());
			assertEquals(userId, user.getModifyUserId());
		}
	}
}

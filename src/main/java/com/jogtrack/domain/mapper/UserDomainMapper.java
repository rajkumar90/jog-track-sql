package com.jogtrack.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.UserDO;
import com.jogtrack.service.contract.Role;
import com.jogtrack.service.contract.User;

@Component
public class UserDomainMapper {
	public UserDO mapToDO(User user) {
		UserDO userDO = new UserDO();
		userDO.setUserId(user.getUserId());
		userDO.setRole(user.getRole().name());
		userDO.setPassword(user.getPassword());
		
		userDO.setCreateTs(user.getCreateTs());
		userDO.setModifyTs(user.getModifyTs());
		userDO.setCreateUserId(user.getCreateUserId());
		userDO.setModifyUserId(user.getModifyUserId());
		return userDO;
	}
	
	public User mapToModel(UserDO userDO) {
		User user = new User();
		user.setUserId(userDO.getUserId());
		user.setPassword(userDO.getPassword());
		user.setRole(Role.valueOf(userDO.getRole()));
		
		user.setCreateTs(userDO.getCreateTs());
		user.setModifyTs(userDO.getModifyTs());
		user.setCreateUserId(userDO.getCreateUserId());
		user.setModifyUserId(userDO.getModifyUserId());
		return user;
	}
	
	public List<User> mapToModel(List<UserDO> userDOList) {
		List<User> userList = new ArrayList<User>();
		for (UserDO userDO : userDOList)
			userList.add(mapToModel(userDO));
		
		return userList;
	}
}

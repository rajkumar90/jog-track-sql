package com.jogtrack.resource.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jogtrack.auth.Secured;
import com.jogtrack.exception.ExceptionMapperProvider;
import com.jogtrack.resource.UserResource;
import com.jogtrack.service.UserServiceImpl;
import com.jogtrack.service.contract.AuthInfo;
import com.jogtrack.service.contract.PaginatedResponse;
import com.jogtrack.service.contract.PaginatedResponseMetadata;
import com.jogtrack.service.contract.PaginationLinks;
import com.jogtrack.service.contract.Role;
import com.jogtrack.service.contract.User;
import com.jogtrack.util.JogTrackConstants;

@Component
@Secured({Role.USER_MANAGER, Role.ADMIN})
public class UserResourceImpl implements UserResource {	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionMapperProvider.class);
	
	@Autowired
	UserServiceImpl userService;
	
	@Override
	@Transactional
	@Secured({Role.USER_MANAGER, Role.ADMIN})
	public User getUser(String userId) {
		LOGGER.debug("Begin getUser()");
		return userService.get(userId);		
	}

	@Override
	@Transactional
	@Secured({Role.USER_MANAGER, Role.ADMIN})
	public User addUser(User user) throws Exception {
		LOGGER.debug("Begin addUser()");
		User addedUser = userService.add(user);
		return addedUser;
	}

	@Override
	@Transactional
	public AuthInfo login(User user) throws Exception {
		LOGGER.debug("Begin login()");
		return userService.login(user.getUserId(), user.getPassword());
	}

	@Override
	@Secured({Role.USER_MANAGER, Role.ADMIN})
	@Transactional
	public User updateUser(User user) {
		LOGGER.debug("Begin updateUser()");
		User updatedUser = userService.update(user);
		return updatedUser;
	}

	@Override
	@Secured({Role.USER_MANAGER, Role.ADMIN})
	@Transactional
	public void deleteUser(String userId) {
		LOGGER.debug("Begin deleteUser()");
		userService.delete(userId);
	}

	@Override
	@Secured({Role.USER_MANAGER, Role.ADMIN})
	public PaginatedResponse<User> getUserList(String filterString, int offset, int limit) {
		LOGGER.debug("Begin getUserList()");
		limit = (limit == 0)? JogTrackConstants.DEFAULT_LIMIT_PAGINATION : limit;
		
		PaginatedResponse<User> response = new PaginatedResponse<User>();
		List<User> userList = userService.getUserList(filterString, offset, limit);
		response.setPayload(userList);
		
		PaginatedResponseMetadata metadata = new PaginatedResponseMetadata();
		metadata.setNumEntries(userList == null ? 0 : userList.size());
		PaginationLinks links = new PaginationLinks();
		links.setSelf("/user?limit=" + limit + "&offset=" + offset);
		int nextOffset = offset + limit;
		links.setNext("/user?limit=" + limit + "&offset=" + nextOffset);
		int prevOffset = offset - limit;
		if (prevOffset >= 0)
			links.setPrev("/user?limit=" + limit + "&offset=" + prevOffset);
		metadata.setLinks(links );
		response.setMetadata(metadata);
		
		return response;
		
	}
	
	

}

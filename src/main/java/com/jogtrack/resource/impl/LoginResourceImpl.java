package com.jogtrack.resource.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jogtrack.exception.ExceptionMapperProvider;
import com.jogtrack.resource.LoginResource;
import com.jogtrack.service.UserServiceImpl;
import com.jogtrack.service.contract.AuthInfo;
import com.jogtrack.service.contract.User;

@Component
public class LoginResourceImpl implements LoginResource {	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionMapperProvider.class);
	
	@Autowired
	UserServiceImpl userService;

	@Override
	@Transactional
	public AuthInfo login(User user) throws Exception {
		LOGGER.debug("Begin login()");
		return userService.login(user.getUserId(), user.getPassword());
	}

}

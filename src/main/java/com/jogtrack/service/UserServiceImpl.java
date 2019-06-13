package com.jogtrack.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jogtrack.dao.AuthInfoDao;
import com.jogtrack.dao.UserDao;
import com.jogtrack.domain.AuthInfoDO;
import com.jogtrack.domain.UserDO;
import com.jogtrack.domain.mapper.AuthInfoDomainMapper;
import com.jogtrack.domain.mapper.UserDomainMapper;
import com.jogtrack.exception.ApplicationException;
import com.jogtrack.exception.ErrorCode;
import com.jogtrack.resource.impl.JogInfoResourceImpl;
import com.jogtrack.service.contract.AuthInfo;
import com.jogtrack.service.contract.User;
import com.jogtrack.util.JogTrackConstants;
import com.jogtrack.util.PasswordUtil;

/**
 * This class contains the business logic for CRUD APIs for User
 * @author raj
 *
 */
@Component
public class UserServiceImpl {
	private static final Logger LOG = LoggerFactory
			.getLogger(JogInfoResourceImpl.class);

	@Autowired
	UserDao userDAO;
	
	@Autowired
	AuthInfoDao authInfoDao;
	
	@Autowired
	UserDomainMapper userDomainMapper;
	
	@Autowired
	AuthInfoDomainMapper authInfoDomainMapper;
	
	@Autowired
	PasswordUtil passwordUtil;
	
	/**
	 * This method adds a user to the system
	 * @param user
	 * @return
	 * @throws ApplicationException 
	 * @throws NoSuchAlgorithmException
	 */
	public User add(User user) throws ApplicationException{
		LOG.debug("Begin add()");
		try {
		String passwordToHash = user.getPassword();
		String hashedPassword = passwordUtil.generateHashedPassword(passwordToHash);
		user.setPassword(hashedPassword);
		UserDO userDO = userDomainMapper.mapToDO(user);
		userDAO.add(userDO);
		} catch (NoSuchAlgorithmException e) {
			ApplicationException ae = new ApplicationException(ErrorCode.SERVICE_UNEXPECTED_ERROR, ErrorCode.SERVICE_UNEXPECTED_ERROR.name(), e);
			throw ae;
		}
		
		return user;
	}
	
	/**
	 * This method gets user details from the system for the given userId
	 * @param userId
	 * @return
	 */
	public User get(String userId) {
		LOG.debug("Begin get()");
		UserDO userDO = userDAO.get(UserDO.class, userId);
		return userDomainMapper.mapToModel(userDO);
	}
	
	/**
	 * This method verifies if the user credentials are valid, and returns an authentication token
	 * @param userId
	 * @param password
	 * @return
	 * @throws ApplicationException 
	 * @throws Exception 
	 */
	public AuthInfo login(String userId, String password) throws ApplicationException {
		LOG.debug("Begin login()");
		if (verifyUserPassword(userId, password)) {
			AuthInfoDO authInfoDO = authInfoDao.get(AuthInfoDO.class, userId);						
			
			String authToken = passwordUtil.generateAuthToken();
			Date now = new Date();
			Date authTokenExpiryDate = new DateTime(now).plusDays(JogTrackConstants.NUM_DAYS_IN_WEEK).toDate();
			AuthInfo authInfo = new AuthInfo();
			// Persist authToken
			if (authInfoDO != null) {
				// Update
				authInfo = authInfoDomainMapper.mapToModel(authInfoDO);
				authInfo.setAuthToken(authToken);
				authInfo.setExpiryDate(authTokenExpiryDate);
				
				authInfoDO = authInfoDomainMapper.mapToDO(authInfo);
				authInfoDao.update(authInfoDO);
			} else {
				// Add new
				authInfo.setUserId(userId);
				authInfo.setAuthToken(authToken);								
				authInfo.setExpiryDate(authTokenExpiryDate);
				authInfoDO = authInfoDomainMapper.mapToDO(authInfo);
				authInfoDao.add(authInfoDO);
			}			
			
			return authInfo;
		} else {
			ApplicationException ae = new ApplicationException(ErrorCode.CLIENT_INVALID_CREDENTIAL, ErrorCode.CLIENT_INVALID_CREDENTIAL.name());
			throw ae;
		}
	}
	
	public boolean verifyUserPassword(String userId, String inputPassword) throws ApplicationException {		
		User user = get(userId);
		String hashedPassword = user.getPassword();
		return passwordUtil.verifyUserPassword(hashedPassword, inputPassword);
	}

	public User update(User user) {
		LOG.debug("Begin update()");
		User userToBeUpdated = userDomainMapper.mapToModel(userDAO.get(UserDO.class, user.getUserId()));
		
		userToBeUpdated.setUserId(user.getUserId());
		userToBeUpdated.setPassword(user.getPassword());
		userToBeUpdated.setRole(user.getRole());
		
		UserDO updatedUserDO = userDAO.update(userDomainMapper.mapToDO(userToBeUpdated));
		return userDomainMapper.mapToModel(updatedUserDO);
	}

	public void delete(String userId) {
		LOG.debug("Begin delete()");
		userDAO.delete(UserDO.class, userId);
	}

	public AuthInfo getTokenDetails(String authToken) {
		LOG.debug("Begin getTokenDetails()");
		AuthInfoDO authInfoDO = authInfoDao.findByAuthToken(authToken);
		if (authInfoDO == null)
			return null;
		return authInfoDomainMapper.mapToModel(authInfoDO);
	}
	
	public String getUserRole(String userId) {		
		LOG.debug("Begin getUserRole()");
		User user = userDomainMapper.mapToModel(userDAO.get(UserDO.class, userId));
		return user.getRole().name();
	}
	
	public List<User> getUserList(String filterString, int offset, int limit) {
		LOG.debug("Begin getUserList()");
		List<UserDO> userDOList = userDAO.getAll(filterString, offset, limit);
		return userDomainMapper.mapToModel(userDOList);
	}
}

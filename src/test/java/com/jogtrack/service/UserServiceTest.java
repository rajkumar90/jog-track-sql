package com.jogtrack.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jogtrack.dao.UserDao;
import com.jogtrack.domain.UserDO;
import com.jogtrack.domain.mapper.UserDomainMapper;
import com.jogtrack.exception.ApplicationException;
import com.jogtrack.service.contract.Role;
import com.jogtrack.service.contract.User;
import com.jogtrack.util.PasswordUtil;

public class UserServiceTest {
	@Mock
	private UserDao userDao;
	
	@Mock
	private UserDomainMapper userDomainMapper;
	
	@Mock
	private PasswordUtil passwordUtil;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetUser() {
		UserDO mockedUserDO = new UserDO();
		mockedUserDO.setUserId("user1");
		mockedUserDO.setPassword("password1");
		mockedUserDO.setRole("REGULAR");
		
		User mockedUser = new User();
		mockedUser.setUserId("user1");
		mockedUser.setPassword("password1");
		mockedUser.setRole(Role.valueOf("REGULAR"));
		when(userDao.get(UserDO.class, "user1")).thenReturn(mockedUserDO);
		when(userDomainMapper.mapToModel(mockedUserDO)).thenReturn(mockedUser);
		
		User user = userService.get("user1");
		assertEquals(user, mockedUser);
		verify(userDao, times(1)).get(UserDO.class, "user1");		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldThrowExceptionOnAdd() throws Exception {
		User user = new User();
		user.setUserId("user1");
		user.setPassword("password1");
		user.setRole(Role.valueOf("REGULAR"));
		when(passwordUtil.generateHashedPassword("password1")).thenThrow(NoSuchAlgorithmException.class);
		
		expectedException.expect(ApplicationException.class);
		userService.add(user);				
	}
	
	@Test
	public void shouldUpdate() {
		User mockedUser = new User();
		mockedUser.setUserId("user1");
		mockedUser.setPassword("password1");
		mockedUser.setRole(Role.valueOf("REGULAR"));
		
		UserDO mockedUserDO = new UserDO();
		mockedUserDO.setUserId("user1");
		mockedUserDO.setPassword("password1");
		mockedUserDO.setRole("REGULAR");
		
		when(userDao.get(UserDO.class, "user1")).thenReturn(mockedUserDO);
		when(userDao.update(mockedUserDO)).thenReturn(mockedUserDO);
		
		when(userDomainMapper.mapToModel(mockedUserDO)).thenReturn(mockedUser);
		when(userDomainMapper.mapToDO(mockedUser)).thenReturn(mockedUserDO);
		
		User user = userService.update(mockedUser);
		assertEquals("user1", user.getUserId());
		assertEquals("password1", user.getPassword());
		assertEquals("REGULAR", user.getRole().name());
	}
	
	@Test
	public void shouldVerifyUser() throws ApplicationException {
		String userId = "userId", hashedPassword = "hashed", inputPassword = "input";
		
		UserDO mockedUserDO = new UserDO();
		mockedUserDO.setUserId(userId);
		mockedUserDO.setPassword(hashedPassword);
		mockedUserDO.setRole("REGULAR");
		
		User mockedUser = new User();
		mockedUser.setUserId(userId);
		mockedUser.setPassword(hashedPassword);
		mockedUser.setRole(Role.valueOf("REGULAR"));
		when(userDao.get(UserDO.class, userId)).thenReturn(mockedUserDO);
		when(userDomainMapper.mapToModel(mockedUserDO)).thenReturn(mockedUser);
		when(passwordUtil.verifyUserPassword(hashedPassword, inputPassword)).thenReturn(true);
		
		assertEquals(true, userService.verifyUserPassword(userId, inputPassword));
		verify(passwordUtil, times(1)).verifyUserPassword(hashedPassword, inputPassword);
		
	}
	
	@Test
	public void shouldDelete() {
		String userId = "regular1";
		userService.delete(userId);
		verify(userDao, times(1)).delete(UserDO.class, userId);
	}
		
}

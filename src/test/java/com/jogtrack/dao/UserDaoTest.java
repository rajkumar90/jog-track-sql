package com.jogtrack.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jogtrack.domain.UserDO;

public class UserDaoTest {
	@Mock
	EntityManager em;
	
	@InjectMocks
	UserDao userDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGet() {
		UserDO mockedUserDO = new UserDO();
		mockedUserDO.setUserId("regular1");
		mockedUserDO.setPassword("abc");
		mockedUserDO.setRole("REGULAR");
		when(em.find(UserDO.class, "regular1")).thenReturn(mockedUserDO);
		
		UserDO userDO = userDao.get(UserDO.class, "regular1");
		assertEquals(userDO, mockedUserDO);
		verify(em, times(1)).find(UserDO.class, "regular1");
	}
	
	@Test
	public void shouldAdd() {
		UserDO userDO = new UserDO();
		userDO.setUserId("regular1");
		userDO.setPassword("abc");
		userDO.setRole("REGULAR");
		userDao.add(userDO);
		verify(em, times(1)).persist(userDO);
	}
	
	@Test
	public void shouldDelete() {
		UserDO mockedUserDO = new UserDO();
		mockedUserDO.setUserId("regular1");
		mockedUserDO.setPassword("abc");
		mockedUserDO.setRole("REGULAR");
		when(em.find(UserDO.class, "regular1")).thenReturn(mockedUserDO);
		userDao.delete(UserDO.class, "regular1");
		
		verify(em, times(1)).remove(mockedUserDO);
	}
	
	@Test
	public void shouldUpdate( ) {
		UserDO userDO = new UserDO();
		userDO.setUserId("regular1");
		userDO.setPassword("abc");
		userDO.setRole("REGULAR");
		userDao.update(userDO);
		
		verify(em, times(1)).merge(userDO);
	}
}

package com.jogtrack.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jogtrack.domain.JogInfoDO;

public class JogInfoDaoTest {
	@Mock
	EntityManager em;
	
	@InjectMocks
	JogInfoDao jogInfoDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGet() {
		JogInfoDO mockJogInfoDO = new JogInfoDO();
		String userId = "regular1", jogId = "jog1";
		BigDecimal val = new BigDecimal(25.5);
		Date date = new Date();
		mockJogInfoDO.setUserId(userId);
		mockJogInfoDO.setJogDate(date);
		mockJogInfoDO.setJogDistance(val);
		mockJogInfoDO.setJogId(jogId);
		mockJogInfoDO.setJogLocationLat(val);
		mockJogInfoDO.setJogLocationLong(val);
		mockJogInfoDO.setJogTime(val);
		mockJogInfoDO.setTemperature(val);
		mockJogInfoDO.setPressure(val);
		mockJogInfoDO.setHumidity(val);
		mockJogInfoDO.setWindSpeed(val);
		when(em.find(JogInfoDO.class, jogId)).thenReturn(mockJogInfoDO);
		
		JogInfoDO jogInfoDO = jogInfoDao.get(JogInfoDO.class, jogId);
		assertEquals(mockJogInfoDO, jogInfoDO);
		verify(em, times(1)).find(JogInfoDO.class, jogId);
	}
	
	@Test
	public void shouldAdd() {
		JogInfoDO jogInfoDO = new JogInfoDO();
		String userId = "regular1", jogId = "jog1";
		BigDecimal val = new BigDecimal(25.5);
		Date date = new Date();
		jogInfoDO.setUserId(userId);
		jogInfoDO.setJogDate(date);
		jogInfoDO.setJogDistance(val);
		jogInfoDO.setJogId(jogId);
		jogInfoDO.setJogLocationLat(val);
		jogInfoDO.setJogLocationLong(val);
		jogInfoDO.setJogTime(val);
		jogInfoDO.setTemperature(val);
		jogInfoDO.setPressure(val);
		jogInfoDO.setHumidity(val);
		jogInfoDO.setWindSpeed(val);
		jogInfoDao.add(jogInfoDO);
		verify(em, times(1)).persist(jogInfoDO);
	}
	
	@Test
	public void shouldDelete() {
		JogInfoDO jogInfoDO = new JogInfoDO();
		String userId = "regular1", jogId = "jog1";
		BigDecimal val = new BigDecimal(25.5);
		Date date = new Date();
		jogInfoDO.setUserId(userId);
		jogInfoDO.setJogDate(date);
		jogInfoDO.setJogDistance(val);
		jogInfoDO.setJogId(jogId);
		jogInfoDO.setJogLocationLat(val);
		jogInfoDO.setJogLocationLong(val);
		jogInfoDO.setJogTime(val);
		jogInfoDO.setTemperature(val);
		jogInfoDO.setPressure(val);
		jogInfoDO.setHumidity(val);
		jogInfoDO.setWindSpeed(val);
		when(em.find(JogInfoDO.class, jogId)).thenReturn(jogInfoDO);
		jogInfoDao.delete(JogInfoDO.class, jogId);
		
		verify(em, times(1)).remove(jogInfoDO);
	}
	
	@Test
	public void shouldUpdate( ) {
		JogInfoDO jogInfoDO = new JogInfoDO();
		String userId = "regular1", jogId = "jog1";
		BigDecimal val = new BigDecimal(25.5);
		Date date = new Date();
		jogInfoDO.setUserId(userId);
		jogInfoDO.setJogDate(date);
		jogInfoDO.setJogDistance(val);
		jogInfoDO.setJogId(jogId);
		jogInfoDO.setJogLocationLat(val);
		jogInfoDO.setJogLocationLong(val);
		jogInfoDO.setJogTime(val);
		jogInfoDO.setTemperature(val);
		jogInfoDO.setPressure(val);
		jogInfoDO.setHumidity(val);
		jogInfoDO.setWindSpeed(val);
		jogInfoDao.update(jogInfoDO);
		
		verify(em, times(1)).merge(jogInfoDO);
	}
}

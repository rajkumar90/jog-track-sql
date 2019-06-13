package com.jogtrack.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jogtrack.dao.JogInfoDao;
import com.jogtrack.domain.JogInfoDO;
import com.jogtrack.domain.mapper.JogInfoDomainMapper;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogLocation;
import com.jogtrack.service.contract.JogWeather;
import com.jogtrack.service.contract.Measurement;
import com.jogtrack.service.contract.UnitOfMeasure;
import com.jogtrack.util.WeatherInfoUtil;

public class JogInfoServiceTest {
	@Mock
	JogInfoDao jogInfoDao;
	
	@Mock
	JogInfoDomainMapper jogInfoDomainMapper;
	
	@Mock
	WeatherInfoUtil weatherInfoUtil;
	
	@InjectMocks
	JogInfoServiceImpl jogInfoService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGet() {
		JogInfoDO mockJogInfoDO = new JogInfoDO();
		String userId = "regular1";
		BigDecimal val = new BigDecimal(25.5);
		Date date = new Date();
		mockJogInfoDO.setUserId(userId);
		mockJogInfoDO.setJogDate(date);
		mockJogInfoDO.setJogDistance(val);
		mockJogInfoDO.setJogId("jog1");
		mockJogInfoDO.setJogLocationLat(val);
		mockJogInfoDO.setJogLocationLong(val);
		mockJogInfoDO.setJogTime(val);
		mockJogInfoDO.setTemperature(val);
		mockJogInfoDO.setPressure(val);
		mockJogInfoDO.setHumidity(val);
		mockJogInfoDO.setWindSpeed(val);
		
		when(jogInfoDao.get(JogInfoDO.class, "jog1")).thenReturn(mockJogInfoDO);
		JogInfo mockJogInfo = new JogInfo();
		mockJogInfo.setUserId(userId);
		mockJogInfo.setJogDate(date);
		mockJogInfo.setJogDistance(new Measurement(val, UnitOfMeasure.KM));
		mockJogInfo.setJogId("jog1");
		JogLocation jogLocation = new JogLocation();
		jogLocation.setLatitude(val);
		jogLocation.setLongitude(val);
		mockJogInfo.setJogLocation(jogLocation);
		
		mockJogInfo.setJogTime(new Measurement(val, UnitOfMeasure.MIN));
		JogWeather jogWeather = new JogWeather();
		jogWeather.setTemperature(new Measurement(val, UnitOfMeasure.C));
		jogWeather.setPressure(new Measurement(val, UnitOfMeasure.HPA));
		jogWeather.setHumidity(new Measurement(val, UnitOfMeasure.NA));
		jogWeather.setWindSpeed(new Measurement(val, UnitOfMeasure.MPS));
		mockJogInfo.setJogWeather(jogWeather);
		
		when(jogInfoDomainMapper.mapToModel(mockJogInfoDO)).thenReturn(mockJogInfo);
		
		JogInfo jogInfo = jogInfoService.getJogInfo("jog1", null);
		assertEquals(userId, jogInfo.getUserId());
		assertEquals(date, jogInfo.getJogDate());
		assertEquals(val, jogInfo.getJogDistance().getMeasurementValue());
		assertEquals(val, jogInfo.getJogLocation().getLatitude());
		assertEquals(val, jogInfo.getJogLocation().getLongitude());
		assertEquals(val, jogInfo.getJogWeather().getTemperature().getMeasurementValue());
	}	
}

package com.jogtrack.domain.mapper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jogtrack.domain.JogInfoDO;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.Measurement;
import com.jogtrack.service.contract.UnitOfMeasure;

public class JogInfoDomainMapperTest {
	JogInfoDomainMapper jogInfoDomainMapper = new JogInfoDomainMapper();
	
	@Test
	public void shouldMapToModel() {
		JogInfoDO jogInfoDO = new JogInfoDO();
		String userId = "regular1";
		BigDecimal val = new BigDecimal(25.50);
		Date date = new Date();
		jogInfoDO.setUserId(userId);
		jogInfoDO.setHumidity(val);
		jogInfoDO.setTemperature(val);
		jogInfoDO.setPressure(val);
		jogInfoDO.setWindSpeed(val);
		jogInfoDO.setJogDistance(val);
		jogInfoDO.setJogLocationLat(val);
		jogInfoDO.setJogLocationLong(val);
		
		jogInfoDO.setJogId("jog1");
		jogInfoDO.setJogTime(val);
		
		jogInfoDO.setCreateTs(date);
		jogInfoDO.setModifyTs(date);
		jogInfoDO.setCreateUserId(userId);
		jogInfoDO.setModifyUserId(userId);
		
		JogInfo jogInfo = jogInfoDomainMapper.mapToModel(jogInfoDO);
		assertEquals(userId, jogInfo.getUserId());
		assertEquals(val, jogInfo.getJogWeather().getHumidity().getMeasurementValue());
		assertEquals(val, jogInfo.getJogWeather().getTemperature().getMeasurementValue());
		assertEquals(val, jogInfo.getJogDistance().getMeasurementValue());
		assertEquals(val, jogInfo.getJogLocation().getLatitude());
		assertEquals(val, jogInfo.getJogLocation().getLongitude());
		assertEquals(val, jogInfo.getJogWeather().getPressure().getMeasurementValue());
		assertEquals(val, jogInfo.getJogWeather().getWindSpeed().getMeasurementValue());
		
		assertEquals(date, jogInfo.getCreateTs());
		assertEquals(date, jogInfo.getModifyTs());
		assertEquals(userId, jogInfo.getCreateUserId());
		assertEquals(userId, jogInfo.getModifyUserId());
	}
	
	@Test
	public void shouldMapToDO() {
		String userId = "regular1";
		BigDecimal val = new BigDecimal(25.50);
		Date date = new Date();
		
		JogInfo jogInfo = new JogInfo();
		jogInfo.setUserId(userId);
		jogInfo.setJogDate(date);
		jogInfo.setJogDistance(new Measurement(val, UnitOfMeasure.KM));
		jogInfo.setJogTime(new Measurement(val, UnitOfMeasure.MIN));
		
		JogInfoDO jogInfoDO = jogInfoDomainMapper.mapToDO(jogInfo);
		
		assertEquals(val, jogInfoDO.getJogDistance());
		assertEquals(val, jogInfoDO.getJogTime());
		assertEquals(date, jogInfoDO.getJogDate());
		assertEquals(userId, jogInfoDO.getUserId());
	}
	
	@Test
	public void shouldMapToModelList() {
		List<JogInfoDO> jogInfoDOList = new ArrayList<JogInfoDO>();
		JogInfoDO jogInfoDO = new JogInfoDO();
		String userId = "regular1";
		BigDecimal val = new BigDecimal(25.50);
		Date date = new Date();
		jogInfoDO.setUserId(userId);
		jogInfoDO.setHumidity(val);
		jogInfoDO.setTemperature(val);
		jogInfoDO.setPressure(val);
		jogInfoDO.setWindSpeed(val);
		jogInfoDO.setJogDistance(val);
		jogInfoDO.setJogLocationLat(val);
		jogInfoDO.setJogLocationLong(val);
		
		jogInfoDO.setJogId("jog1");
		jogInfoDO.setJogTime(val);
		
		jogInfoDO.setCreateTs(date);
		jogInfoDO.setModifyTs(date);
		jogInfoDO.setCreateUserId(userId);
		jogInfoDO.setModifyUserId(userId);
		
		for (int i = 0; i < 10; i++)
			jogInfoDOList.add(jogInfoDO);
		
		List<JogInfo> jogInfoList = jogInfoDomainMapper.mapToModelList(jogInfoDOList);
		for (int i = 0; i < 10; i++) {
			JogInfo jogInfo = jogInfoList.get(i);
			assertEquals(userId, jogInfo.getUserId());
			assertEquals(val, jogInfo.getJogWeather().getHumidity().getMeasurementValue());
			assertEquals(val, jogInfo.getJogWeather().getTemperature().getMeasurementValue());
			assertEquals(val, jogInfo.getJogDistance().getMeasurementValue());
			assertEquals(val, jogInfo.getJogLocation().getLatitude());
			assertEquals(val, jogInfo.getJogLocation().getLongitude());
			assertEquals(val, jogInfo.getJogWeather().getPressure().getMeasurementValue());
			assertEquals(val, jogInfo.getJogWeather().getWindSpeed().getMeasurementValue());
			
			assertEquals(date, jogInfo.getCreateTs());
			assertEquals(date, jogInfo.getModifyTs());
			assertEquals(userId, jogInfo.getCreateUserId());
			assertEquals(userId, jogInfo.getModifyUserId());
		}
		
	}
}

package com.jogtrack.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jogtrack.domain.JogInfoDO;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogLocation;
import com.jogtrack.service.contract.JogWeather;
import com.jogtrack.service.contract.Measurement;
import com.jogtrack.service.contract.UnitOfMeasure;

@Component
public class JogInfoDomainMapper {
	public JogInfoDO mapToDO(JogInfo jogInfo) {
		JogInfoDO jogInfoDO = new JogInfoDO();
		jogInfoDO.setJogId(jogInfo.getJogId());

		jogInfoDO.setJogDate(jogInfo.getJogDate());
		jogInfoDO.setJogDistance(jogInfo.getJogDistance().getMeasurementValue());
		jogInfoDO.setJogTime(jogInfo.getJogTime().getMeasurementValue());
		if (jogInfo.getJogLocation() != null) {
			jogInfoDO.setJogLocationLat(jogInfo.getJogLocation().getLatitude());
			jogInfoDO.setJogLocationLong(jogInfo.getJogLocation().getLongitude());
		}
		if (jogInfo.getJogWeather() != null) {
			jogInfoDO.setHumidity(jogInfo.getJogWeather().getHumidity().getMeasurementValue());
			jogInfoDO.setPressure(jogInfo.getJogWeather().getPressure().getMeasurementValue());
			jogInfoDO.setTemperature(jogInfo.getJogWeather().getTemperature().getMeasurementValue());
			jogInfoDO.setWindSpeed(jogInfo.getJogWeather().getWindSpeed().getMeasurementValue());
		}
		jogInfoDO.setUserId(jogInfo.getUserId());
		jogInfoDO.setCreateTs(jogInfo.getCreateTs());
		jogInfoDO.setModifyTs(jogInfo.getModifyTs());
		jogInfoDO.setCreateUserId(jogInfo.getCreateUserId());
		jogInfoDO.setModifyUserId(jogInfoDO.getModifyUserId());
		return jogInfoDO;
	}

	public JogInfo mapToModel(JogInfoDO jogInfoDO) {
		JogInfo jogInfo = new JogInfo();
		jogInfo.setJogId(jogInfoDO.getJogId());
		jogInfo.setUserId(jogInfoDO.getUserId());
		jogInfo.setJogDate(jogInfoDO.getJogDate());
		jogInfo.setJogDistance(new Measurement(jogInfoDO.getJogDistance(), UnitOfMeasure.KM));
		jogInfo.setJogTime(new Measurement(jogInfoDO.getJogTime(), UnitOfMeasure.MIN));

		JogLocation jogLocation = new JogLocation();
		jogLocation.setLatitude(jogInfoDO.getJogLocationLat());
		jogLocation.setLongitude(jogInfoDO.getJogLocationLong());
		jogInfo.setJogLocation(jogLocation);
		JogWeather jogWeather = new JogWeather();
		jogWeather.setHumidity(new Measurement(jogInfoDO.getHumidity(), UnitOfMeasure.NA));
		jogWeather.setPressure(new Measurement(jogInfoDO.getPressure(), UnitOfMeasure.HPA));
		jogWeather.setTemperature(new Measurement(jogInfoDO.getTemperature(), UnitOfMeasure.C));
		jogWeather.setWindSpeed(new Measurement(jogInfoDO.getWindSpeed(), UnitOfMeasure.MPS));
		jogInfo.setJogWeather(jogWeather);

		jogInfo.setCreateTs(jogInfoDO.getCreateTs());
		jogInfo.setModifyTs(jogInfoDO.getModifyTs());
		jogInfo.setCreateUserId(jogInfoDO.getCreateUserId());
		jogInfo.setModifyUserId(jogInfoDO.getModifyUserId());
		return jogInfo;
	}

	public List<JogInfo> mapToModelList(List<JogInfoDO> jogInfoDOList) {
		List<JogInfo> jogInfoList = new ArrayList<JogInfo>();
		for (JogInfoDO jogInfoDO : jogInfoDOList)
			jogInfoList.add(this.mapToModel(jogInfoDO));

		return jogInfoList;
	}
}

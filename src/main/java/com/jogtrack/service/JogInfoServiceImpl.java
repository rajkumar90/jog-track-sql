package com.jogtrack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jogtrack.dao.JogInfoDao;
import com.jogtrack.domain.JogInfoDO;
import com.jogtrack.domain.mapper.JogInfoDomainMapper;
import com.jogtrack.exception.AuthorizationException;
import com.jogtrack.exception.ErrorCode;
import com.jogtrack.resource.impl.JogInfoResourceImpl;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogReport;
import com.jogtrack.service.contract.JogWeather;
import com.jogtrack.service.contract.Measurement;
import com.jogtrack.service.contract.Role;
import com.jogtrack.service.contract.UnitOfMeasure;
import com.jogtrack.util.JogTrackConstants;
import com.jogtrack.util.WeatherInfoUtil;
import com.jogtrack.weatherinfo.response.WeatherInfoResponse;

/**
 * This class contains the implementation for the CRUD APIs for JogInfo
 * @author raj
 *
 */
@Component
public class JogInfoServiceImpl implements JogInfoService {
	private static final Logger LOG = LoggerFactory
			.getLogger(JogInfoResourceImpl.class);

	@Autowired
	JogInfoDao jogInfoDao;
	
	@Autowired
	JogInfoDomainMapper jogInfoDomainMapper;
	
	@Autowired
	WeatherInfoUtil weatherInfoUtil;
	
	public void authorizeUser(String userId, HttpServletRequest requestContext) throws Exception {
		LOG.debug("Begin authorizeUser()");
		String authenticateduserRole = (String) requestContext.getAttribute(JogTrackConstants.USER_ROLE);
		String autheticatedUserId = (String) requestContext.getAttribute(JogTrackConstants.USERID_PARAM);
		if (Role.REGULAR.name().equals(authenticateduserRole) && !userId.equals(autheticatedUserId))
			throw new AuthorizationException(ErrorCode.CLIENT_FORBIDDEN_ACCESS, JogTrackConstants.INSUFFICIENT_PERMISSION);
	}
	
	@Override
	public JogInfo addJogInfo(JogInfo jogInfo, HttpServletRequest requestContext) throws Exception {
		LOG.debug("Begin addJogInfo()");
		authorizeUser(jogInfo.getUserId(), requestContext);
		WeatherInfoResponse weatherInfoResponse = weatherInfoUtil.getWeatherInfo(jogInfo);
		JogWeather jogWeather = new JogWeather();
		jogWeather.setTemperature(new Measurement(weatherInfoResponse.getCurrently().getTemperature(), UnitOfMeasure.C));
		jogWeather.setHumidity(new Measurement(weatherInfoResponse.getCurrently().getHumidity(), UnitOfMeasure.NA));
		jogWeather.setPressure(new Measurement(weatherInfoResponse.getCurrently().getPressure(), UnitOfMeasure.HPA));
		jogWeather.setWindSpeed(new Measurement(weatherInfoResponse.getCurrently().getWindSpeed(), UnitOfMeasure.MPS));
		
		jogInfo.setJogWeather(jogWeather);
		jogInfoDao.add(jogInfoDomainMapper.mapToDO(jogInfo));
		return jogInfo;
	}

	@Override
	public List<JogInfo> getJogInfoList(String userId, String jogId, String filterString, int offset, int limit, HttpServletRequest requestContext) throws Exception {
		LOG.debug("Begin getJogInfoList()");
		if (userId != null) {
			authorizeUser(userId, requestContext);
		}		
		List<JogInfo> jogInfoList = new ArrayList<>();
		List<JogInfoDO> jogInfoDOList = jogInfoDao.getAll(userId, jogId, filterString, offset, limit);
		jogInfoList = jogInfoDomainMapper.mapToModelList(jogInfoDOList);
		return jogInfoList;
	}

	@Override
	public JogInfo getJogInfo(String jogId, HttpServletRequest requestContext) {
		LOG.debug("Begin getJogInfo()");
		JogInfo jogInfo = jogInfoDomainMapper.mapToModel(jogInfoDao.get(JogInfoDO.class, jogId));
		return jogInfo;
	}

	@Override
	public JogInfo update(JogInfo jogInfo, HttpServletRequest requestContext) throws Exception {
		LOG.debug("Begin update()");
		// When jog-info is updated, the jog time or location may be different
		// Hence call the weather info provider to get updated weather conditions
		authorizeUser(jogInfo.getUserId(), requestContext);
		JogInfo jogInfoToBeUpdated = getJogInfo(jogInfo.getJogId(), requestContext);
		
		jogInfoToBeUpdated.setJogDate(jogInfo.getJogDate());
		jogInfoToBeUpdated.setJogDistance(jogInfo.getJogDistance());
		jogInfoToBeUpdated.setJogLocation(jogInfo.getJogLocation());
		jogInfoToBeUpdated.setUserId(jogInfo.getUserId());
		
		WeatherInfoResponse weatherInfoResponse = weatherInfoUtil.getWeatherInfo(jogInfo);
		JogWeather jogWeather = new JogWeather();
		jogWeather.setTemperature(new Measurement(weatherInfoResponse.getCurrently().getTemperature(), UnitOfMeasure.C));
		jogWeather.setHumidity(new Measurement(weatherInfoResponse.getCurrently().getHumidity(), UnitOfMeasure.NA));
		jogWeather.setPressure(new Measurement(weatherInfoResponse.getCurrently().getPressure(), UnitOfMeasure.HPA));
		jogWeather.setWindSpeed(new Measurement(weatherInfoResponse.getCurrently().getWindSpeed(), UnitOfMeasure.MPS));

		jogInfoToBeUpdated.setJogWeather(jogWeather);
		jogInfoDao.update(jogInfoDomainMapper.mapToDO(jogInfoToBeUpdated));
		return jogInfoDomainMapper.mapToModel(jogInfoDao.get(JogInfoDO.class, jogInfo.getJogId()));
	}

	@Override
	public void delete(String userId, String jogId, HttpServletRequest requestContext) throws Exception {
		LOG.debug("Begin delete()");
		authorizeUser(userId, requestContext);
		jogInfoDao.delete(JogInfoDO.class, jogId);
		
	}

	@Override
	public JogReport getReport(String userId, HttpServletRequest requestContext) {
		LOG.debug("Begin getReport()");
		Date date = new Date();
		Date daysAgo = new DateTime(date).minusDays(JogTrackConstants.NUM_DAYS_IN_WEEK).toDate();
		Tuple tuple = jogInfoDao.getReport(userId, daysAgo);
		JogReport jogReport = new JogReport();				
		
		jogReport.setUserId(userId);
		// Default to weekly
		jogReport.setReportType("WEEKLY");
		
		long numJogs = tuple.get(3, Long.class);
		jogReport.setNumJogs(numJogs);
		if (numJogs > 0) {
			BigDecimal averageDistance = new BigDecimal(tuple.get(0, Double.class));
			averageDistance = averageDistance.setScale(2, RoundingMode.HALF_UP);
			jogReport.setAverageDistance(new Measurement(averageDistance, UnitOfMeasure.KM));
			BigDecimal totalDistance = tuple.get(1, BigDecimal.class);
			BigDecimal totalTime = tuple.get(2, BigDecimal.class);
			BigDecimal averageSpeed = totalDistance.divide(totalTime, 2, RoundingMode.HALF_UP)
					.multiply(new BigDecimal(60));
			averageSpeed = averageSpeed.setScale(2, RoundingMode.HALF_UP);
			jogReport.setAverageSpeed(new Measurement(averageSpeed, UnitOfMeasure.KMPH));
		} else {
			// Set values to 0
			jogReport.setNumJogs(0L);
			jogReport.setAverageDistance(new Measurement(BigDecimal.ZERO, UnitOfMeasure.KM));
			jogReport.setAverageSpeed(new Measurement(BigDecimal.ZERO, UnitOfMeasure.KMPH));
		}
		
		return jogReport;
	}

}

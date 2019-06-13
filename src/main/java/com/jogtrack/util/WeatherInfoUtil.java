package com.jogtrack.util;

import java.io.IOException;
import static com.jogtrack.util.JogTrackConstants.*;
import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.jogtrack.service.common.ObjectMapperFactory;
import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.weatherinfo.response.WeatherInfoResponse;
import com.mashape.unirest.http.Unirest;

@Component
public class WeatherInfoUtil {
	/**
	 * This method gets the weather details from the 3rd party weather provider
	 * @param jogInfo
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */	
	public WeatherInfoResponse getWeatherInfo(JogInfo jogInfo) throws Exception {
		String latitude = String.valueOf(jogInfo.getJogLocation().getLatitude());
		String longitude = String.valueOf(jogInfo.getJogLocation().getLongitude());
		Date jogTime = jogInfo.getJogDate();
		Long unixTime = jogTime.getTime() / 1000;
		String apiUrl = WEATHER_INFO_API_BASE_URL + WEATHER_INFO_API_KEY + "/" + latitude + "," + longitude + "," + unixTime;
		apiUrl = apiUrl + WEATHER_INFO_API_QUERY_STRING;
		
		String weatherInfoResponseStr = Unirest.get(apiUrl).asString().getBody();
		return ObjectMapperFactory.getObjectMapper().readValue(weatherInfoResponseStr, WeatherInfoResponse.class);
	}
	
}

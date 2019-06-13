package com.jogtrack.util;

public class JogTrackConstants {
	private JogTrackConstants() {};
	
	public static final String SHA_512 = "SHA-512";
	public static final String COLON = ":";
	public static final String INVALID_USERID_OR_PASSWORD = "Invalid userId or password";
	public static final String INVALID_TOKEN = "Invalid token";
	public static final String INSUFFICIENT_PERMISSION = "User does not have permission to perform this operation";
	public static final String APPLICATION_JSON = "application/json";
	public static final String USERID_PARAM = "userId";
	public static final String JOGID_PARAM = "jogId";
	public static final String FILTER_PARAM = "filter";
	public static final String OFFSET_QUERY_PARAM = "offset";
	public static final String LIMIT_QUERY_PARAM = "limit";
	public static final String USER_ROLE = "userRole";
	// Default page size
	public static final int DEFAULT_LIMIT_PAGINATION = 10;
	public static final int NUM_DAYS_IN_WEEK = 7;
	
	public static final String WEATHER_INFO_API_BASE_URL = "https://api.darksky.net/forecast/";
	public static final String WEATHER_INFO_API_KEY = "be3624ad85d59a98e6c86e765a2fc6f1";
	public static final String WEATHER_INFO_API_QUERY_STRING = "?exclude=hourly,daily,flags&units=si";
	
	public static final String APPLICATION_USER = "app";
}

package com.jogtrack.util;

import java.io.IOException;
import java.util.Date;

import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * This class is used for convert the json value to the Date type
 * @author raj
 *
 */

public class JsonDateDeserializer  extends JsonDeserializer<Date>  {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String dateString = jp.getText();
		Date date = null;
		if( dateString != null && !dateString.equals("")){
			//Changed to a format that does not fail when milliseconds are passed
			org.joda.time.format.DateTimeFormatter dtf = ISODateTimeFormat.dateTimeParser();
			date = dtf.parseDateTime(dateString).toDate();
		}
		return date;
	}
}

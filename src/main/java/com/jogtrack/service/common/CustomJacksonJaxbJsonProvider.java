package com.jogtrack.service.common;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * 
 * @author msing23
 *
 */
public class CustomJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {

	public CustomJacksonJaxbJsonProvider() {
		super();
		this.setMapper(ObjectMapperFactory.getObjectMapper());
	}

}

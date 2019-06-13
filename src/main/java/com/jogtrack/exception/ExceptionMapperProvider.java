package com.jogtrack.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jogtrack.service.contract.Error;
import com.jogtrack.service.contract.ErrorResponse;
import com.jogtrack.util.JogTrackConstants;

/**
 * Provider for mapping exceptions,for returns platform services.
 * 
 * @author msing23
 *
 */
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Provider
@Component
public final class ExceptionMapperProvider extends AbstractExceptionMapper
		implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionMapperProvider.class);

	@Override
	public Response toResponse(final Exception exception) {
		LOGGER.error("Exception occured : " + exception);
		Response response = null;
		if (exception instanceof AuthorizationException) {
			ErrorResponse error = new ErrorResponse(new Error(ErrorCode.CLIENT_INVALID_CREDENTIAL.getErrorCode(), JogTrackConstants.INVALID_TOKEN));
			response = Response.status(Response.Status.FORBIDDEN).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
		else if (exception instanceof ApplicationException) {
			ErrorResponse error = new ErrorResponse(new Error(ErrorCode.SERVICE_ERROR.getErrorCode(), "Exception occured in the application while processing the request"));
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).type(MediaType.APPLICATION_JSON).build();
		} else {
			ErrorResponse error = new ErrorResponse(new Error(ErrorCode.SERVICE_UNEXPECTED_ERROR.getErrorCode(), "Unexpected error occured while processing the request"));
			response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).type(MediaType.APPLICATION_JSON).build();
		}
		return response;
	}

}
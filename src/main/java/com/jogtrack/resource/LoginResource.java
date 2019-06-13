package com.jogtrack.resource;

import static com.jogtrack.util.JogTrackConstants.APPLICATION_JSON;
import static com.jogtrack.util.JogTrackConstants.FILTER_PARAM;
import static com.jogtrack.util.JogTrackConstants.USERID_PARAM;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.jogtrack.service.contract.AuthInfo;
import com.jogtrack.service.contract.PaginatedResponse;
import com.jogtrack.service.contract.User;
import com.jogtrack.util.JogTrackConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This is the resource class for the CRUD APIs for user details
 * @author raj
 *
 */
@Path("/login")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Api(value = "/login")
public interface LoginResource {
			
	@POST
	@Path("/")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public AuthInfo login(User user) throws Exception;
}

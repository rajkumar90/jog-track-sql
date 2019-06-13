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

/**
 * This is the resource class for the CRUD APIs for user details
 * @author raj
 *
 */
@Path("/user")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Api(value = "/user")
public interface UserResource {
	
	@GET
	@Path("/{userId}")
	@Produces(APPLICATION_JSON)
	public User getUser(@PathParam(USERID_PARAM) String userId) throws Exception;
	
	@GET
	@Path("/")
	@Produces(APPLICATION_JSON)
	public PaginatedResponse<User> getUserList(@QueryParam(FILTER_PARAM) String filterString, 
			@QueryParam(JogTrackConstants.OFFSET_QUERY_PARAM) int offset,
			@QueryParam(JogTrackConstants.LIMIT_QUERY_PARAM) int limit) throws Exception;
	
	@POST
	@Path("/")
	@Produces(APPLICATION_JSON)
	public User addUser(User user) throws Exception;
	
	@PUT
	@Path("/")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public User updateUser(User user) throws Exception;
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam(USERID_PARAM) String userId) throws Exception;
	
	@POST
	@Path("/login")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public AuthInfo login(User user) throws Exception;
}

package com.jogtrack.resource;

import static com.jogtrack.util.JogTrackConstants.APPLICATION_JSON;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.jogtrack.service.contract.JogInfo;
import com.jogtrack.service.contract.JogReport;
import com.jogtrack.service.contract.PaginatedResponse;
import com.jogtrack.util.JogTrackConstants;

import io.swagger.annotations.Api;

/**
 * This is the resource class for the CRUD APIs for jog info details
 * @author raj
 *
 */
@Path("/joginfo")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Api(value = "/jogInfo")
public interface JogInfoResource {
	
	@GET
	@Path("/")
	@Produces(APPLICATION_JSON)
	public PaginatedResponse<JogInfo> getJogInfoList(@QueryParam(JogTrackConstants.USERID_PARAM) String userId,
			@QueryParam(JogTrackConstants.JOGID_PARAM) String jogId,
			@QueryParam(JogTrackConstants.FILTER_PARAM) String filter,
			@QueryParam(JogTrackConstants.OFFSET_QUERY_PARAM) int offset,
			@QueryParam(JogTrackConstants.LIMIT_QUERY_PARAM) int limit,
			@Context HttpServletRequest request) throws Exception;

	@POST
	@Path("/")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JogInfo addJogInfo(JogInfo jogInfo, @Context HttpServletRequest request) throws Exception;
	
	@PUT
	@Path("/")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public JogInfo updateJogInfo(JogInfo jogInfo, @Context HttpServletRequest request) throws Exception;
	
	@DELETE
	@Path("/user/{userId}/jog/{jogId}")
	@Consumes(APPLICATION_JSON)
	public void deleteJogInfo(@PathParam(JogTrackConstants.USERID_PARAM) String userId, @PathParam(JogTrackConstants.JOGID_PARAM) String jogId, @Context HttpServletRequest request) throws Exception;
	
	// This method generates weekly report as of now. If needed, a query param can be added to generate WEEKLY/MONTHLY/QUARTERLY reports
	@GET
	@Path("/report/{userId}")
	@Produces(APPLICATION_JSON)
	public JogReport getReport(@PathParam(JogTrackConstants.USERID_PARAM) String userId, @Context HttpServletRequest request);
	
}

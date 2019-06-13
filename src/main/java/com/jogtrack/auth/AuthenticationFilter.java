package com.jogtrack.auth;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.net.HttpHeaders;
import com.jogtrack.exception.AuthenticationException;
import com.jogtrack.exception.ErrorCode;
import com.jogtrack.service.UserServiceImpl;
import com.jogtrack.service.contract.AuthInfo;
import com.jogtrack.service.contract.Error;
import com.jogtrack.service.contract.ErrorResponse;
import com.jogtrack.util.JogTrackConstants;

/**
 * JAX-RS filter for authenticating API requests
 * @author raj
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	@Autowired	
	UserServiceImpl userService;

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                            .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Validate the token
        	validateTokenAndSetUserId(token, requestContext);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
    	ErrorResponse error = new ErrorResponse(new Error(ErrorCode.CLIENT_INVALID_CREDENTIAL.getErrorCode(), JogTrackConstants.INVALID_TOKEN));
    	requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity(error).type(MediaType.APPLICATION_JSON)
                        .build());
    }

    private void validateTokenAndSetUserId(String token, ContainerRequestContext requestContext) throws Exception {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
    	AuthInfo authInfo = userService.getTokenDetails(token);
    	
    	Date tokenExpiryDate = authInfo.getExpiryDate();
		Date now = new Date();
		if (tokenExpiryDate.before(now))			
    		throw new AuthenticationException(ErrorCode.CLIENT_INVALID_CREDENTIAL, JogTrackConstants.INVALID_TOKEN);
		requestContext.setProperty(JogTrackConstants.USERID_PARAM, authInfo.getUserId());
    }
}
package com.jogtrack.auth;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.jogtrack.exception.ErrorCode;
import com.jogtrack.service.UserServiceImpl;
import com.jogtrack.service.contract.Error;
import com.jogtrack.service.contract.ErrorResponse;
import com.jogtrack.service.contract.Role;
import com.jogtrack.util.JogTrackConstants;

/**
 * JAX-RS filter for authorizing API requests
 * @author raj
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    
    @Autowired
    private UserServiceImpl userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	// Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);

        try {
        	String userId = (String)requestContext.getProperty(JogTrackConstants.USERID_PARAM);
        	String userRole = userService.getUserRole(userId);
            requestContext.setProperty(JogTrackConstants.USER_ROLE, userRole);
            
//            servletRequest.setAttribute(JogTrackConstants.USER_ROLE, userRole);
//            servletRequest.setAttribute(JogTrackConstants.USERID_PARAM, userId);
            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, userRole);
            } else {
                checkPermissions(methodRoles, userRole);
            }
        } catch (Exception e) {
        	ErrorResponse error = new ErrorResponse(new Error(ErrorCode.CLIENT_FORBIDDEN_ACCESS.getErrorCode(), JogTrackConstants.INSUFFICIENT_PERMISSION));
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN).entity(error).type(MediaType.APPLICATION_JSON).build());
        }
    }

    // Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<Role> allowedRoles, String userRole) throws Exception {
        // Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method
    	
    	Role roleEnum = Role.valueOf(userRole);
    	if (!allowedRoles.contains(roleEnum))
    		throw new Exception(JogTrackConstants.INSUFFICIENT_PERMISSION);
    }
}
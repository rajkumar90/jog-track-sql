package com.jogtrack.exception;

public class AuthorizationException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 616569670560262444L;

	public AuthorizationException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public AuthorizationException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}

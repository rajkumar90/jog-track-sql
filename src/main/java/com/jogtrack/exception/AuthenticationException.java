package com.jogtrack.exception;

public class AuthenticationException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 616569670560262444L;

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public AuthenticationException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}

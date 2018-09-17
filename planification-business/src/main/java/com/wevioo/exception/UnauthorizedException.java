package com.wevioo.exception;


/**
 * Unauthorized exception
 */
public class UnauthorizedException extends RestException {

	private static final long serialVersionUID = -3845612527659235601L;

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String code, String message) {
		super(code, message);
	}

}

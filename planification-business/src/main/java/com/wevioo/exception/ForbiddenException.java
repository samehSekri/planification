package com.wevioo.exception;


/**
 * Entity already exist exception
 */
public class ForbiddenException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public ForbiddenException(String code, String message) {
		super(code, message);
	}

	public ForbiddenException(String message) {
		super(message);
	}

}

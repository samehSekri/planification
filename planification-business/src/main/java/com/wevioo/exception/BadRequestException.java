package com.wevioo.exception;


/**
 * Missing required data exception
 */
public class BadRequestException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String code, String message) {
		super(code, message);
	}

	public BadRequestException(String code, String message, Object data) {
		super(code, message, data);
	}

}

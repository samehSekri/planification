package com.wevioo.exception;


/**
 * Entity already exist exception
 */
public class AlreadyExistsException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public AlreadyExistsException(String code, String message) {
		super(code, message);
	}

	public AlreadyExistsException(String message) {
		super(message);
	}

}

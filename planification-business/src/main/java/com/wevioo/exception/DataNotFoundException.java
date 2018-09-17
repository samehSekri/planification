package com.wevioo.exception;


/**
 * Data not found exception. It will return the Not found HTTP status code
 */
public class DataNotFoundException extends RestException {

	private static final long serialVersionUID = -3260745311457353808L;

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String code, String message) {
		super(code, message);
	}

}

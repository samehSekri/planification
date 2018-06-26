package com.wevioo.exception;

public class BatchException extends Exception {

	private static final long serialVersionUID = -673714638647024279L;
	private String message;

	public BatchException(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}

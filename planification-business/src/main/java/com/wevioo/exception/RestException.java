package com.wevioo.exception;


import lombok.Getter;
import lombok.Setter;
/**
 * Generic REST Exception.
 */
@Getter
@Setter
public class RestException extends Exception {

	private static final long serialVersionUID = -8041597068126222870L;

	private String code;
	private String message;
	private Object data;

	public RestException(String message) {
		super(message);
		this.message = message;
	}

	public RestException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public RestException(String code, String message, Object data) {
		super(message);
		this.code = code;
		this.message = message;
		this.data = data;
	}
}

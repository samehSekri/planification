package com.wevioo.model.json;

import java.io.Serializable;

public class HttpResponseBodyJson implements Serializable {
	
	private static final long serialVersionUID = -2867551254217421549L;
	
	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;
	
	public HttpResponseBodyJson(){
		super();
	}
	
	public HttpResponseBodyJson(long timestamp,int status,String error,String exception,String message,String path){
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.exception = exception;
		this.message = message;
		this.path = path;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}

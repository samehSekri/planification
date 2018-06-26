package com.wevioo.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;


// The api error will catch the http response and format it
// It will be used as exception catcher and converted to json with Object Mapper 

public class ApiException {
    
    private HttpStatus status;
    private String message;
    private List<String> errors;
    
    public ApiException(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    
    public ApiException(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }
    
    public ApiException() {}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }   
    
}

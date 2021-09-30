package com.upload.messages;

import org.springframework.http.HttpStatus;

public class ValidationExceptionMessageError extends ExceptionMessageError {

	private String field;
	
	public ValidationExceptionMessageError(String message, HttpStatus status) {
		super(message, status);
	}
	
	public ValidationExceptionMessageError(String message, String field, HttpStatus status) {
		super(message, status);
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}

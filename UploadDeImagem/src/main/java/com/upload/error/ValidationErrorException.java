package com.upload.error;

import org.springframework.validation.BindingResult;

public class ValidationErrorException extends NullPointerException{

	private static final long serialVersionUID = 1L;
	
	private String field;
	private String message;
	
	public ValidationErrorException(BindingResult err) {
		super(err.getFieldError().getDefaultMessage());
		this.message = err.getFieldError().getDefaultMessage();
		this.field = err.getFieldError().getField();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

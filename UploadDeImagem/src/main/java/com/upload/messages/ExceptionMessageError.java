package com.upload.messages;

import org.springframework.http.HttpStatus;

public class ExceptionMessageError {
	
	private String message;
	private HttpStatus status;
	private Integer statusCode;
	
	public ExceptionMessageError(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
		this.statusCode = status.value();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ExceptionMessageError [message=" + message + ", status=" + status + ", statusCode=" + statusCode + "]";
	}
}

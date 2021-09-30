package com.upload.error;


public class UploadInternalServerErrorException extends NullPointerException{
	
	private static final long serialVersionUID = 1L;

	public UploadInternalServerErrorException(String message) {
		super(message);
	}
}

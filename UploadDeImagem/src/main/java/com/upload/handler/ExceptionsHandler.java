package com.upload.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.upload.error.MimeTypeException;
import com.upload.error.UploadInternalServerErrorException;
import com.upload.error.ValidationErrorException;
import com.upload.messages.ExceptionMessageError;
import com.upload.messages.ValidationExceptionMessageError;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	private ResponseEntity<Object> handleFileSizeLimitExceededException(MaxUploadSizeExceededException ex) {
		
		return new ResponseEntity<>(new ExceptionMessageError("O arquivo deve ter no máximo 2MB", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MultipartException.class)
	private ResponseEntity<Object> handleMultipartException(MultipartException ex) {
		
		return new ResponseEntity<>(new ExceptionMessageError("Este formulário é do tipo multipart", HttpStatus.BAD_REQUEST),  HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionMessageError("Houve um erro no corpo da requisicao, identifique o erro e corrija-o", status), status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(new ValidationExceptionMessageError("Por Favor, informe um arquivo de imagem", "img", status), status);
	}
	
	@ExceptionHandler(MimeTypeException.class)
	private ResponseEntity<Object> handleMimeTypeException(MimeTypeException ex) {
		
		return new ResponseEntity<>(new ExceptionMessageError(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UploadInternalServerErrorException.class)
	private ResponseEntity<Object> handleUploadInternalServerErrorException(UploadInternalServerErrorException ex) {
		
		return new ResponseEntity<>(new ExceptionMessageError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidationErrorException.class)
	private ResponseEntity<Object> handlerValidationErrorException(ValidationErrorException ex) {
		
		return new ResponseEntity<>(new ValidationExceptionMessageError(ex.getMessage(), ex.getField(),HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<Object> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		
		return new ResponseEntity<>(new ExceptionMessageError("Você deve passar apenas números inteiros como parâmetro",HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionMessageError("Metodo não suportado", status), status);
	}
	
}
package com.olxlogin.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {InvalidUserException.class})
	public ResponseEntity<Object> handleInvalidUser(RuntimeException re, WebRequest webRequest){
		return handleExceptionInternal(re, re.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value = {InvalidTokenException.class})
	public ResponseEntity<Object> handleInvalidToken(RuntimeException re, WebRequest webRequest){
		return handleExceptionInternal(re, re.toString(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, webRequest);
	}
}

package com.olxlogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException{

	private String message;

	public InvalidTokenException() {}
			
	public InvalidTokenException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid " + this.message;
	}
}

package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You are not authorized to take this action.")
public class AuthorizationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public AuthorizationException() {
		super("Unauthorized user");
		this.httpStatus = HttpStatus.FORBIDDEN;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}

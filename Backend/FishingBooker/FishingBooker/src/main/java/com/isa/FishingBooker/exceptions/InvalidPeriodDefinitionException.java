package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Start date is after end date.")
public class InvalidPeriodDefinitionException extends RuntimeException {

	private static final long serialVersionUID = -9185256499808865997L;

	public InvalidPeriodDefinitionException() {
		super("Start date is after end date.");
	}
}

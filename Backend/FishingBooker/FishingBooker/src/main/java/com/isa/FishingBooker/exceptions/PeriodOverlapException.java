package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Periods overlap")
public class PeriodOverlapException extends RuntimeException{
	public PeriodOverlapException() {
	}
}

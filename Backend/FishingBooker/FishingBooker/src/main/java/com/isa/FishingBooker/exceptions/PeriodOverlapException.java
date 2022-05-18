package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Period overlap with an existing period.")
public class PeriodOverlapException extends RuntimeException{
	public PeriodOverlapException() {
	}
}

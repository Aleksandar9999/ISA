package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Defined number of users is not valid for this service.")
public class InvalidNumberOfPersonsException extends RuntimeException {

}

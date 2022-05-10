package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Price with specified number of days already exists.")
public class ServicePriceAlreadyExistException extends RuntimeException {

}

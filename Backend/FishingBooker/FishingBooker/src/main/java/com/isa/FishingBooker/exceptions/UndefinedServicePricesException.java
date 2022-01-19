package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "There is no price for this service or appointment cannot be created for specified number of days.")
public class UndefinedServicePricesException extends RuntimeException{

}

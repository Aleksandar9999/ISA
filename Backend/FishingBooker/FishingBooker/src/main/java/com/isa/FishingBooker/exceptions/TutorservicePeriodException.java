package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Cannot create appoitment, invalid or there is no available period.")
public class TutorservicePeriodException extends RuntimeException {

}

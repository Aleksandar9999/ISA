package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cannot update service with existing pending appointments.")
public class PendingAppointmentExistException extends RuntimeException {

}

package com.isa.FishingBooker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Revision cannot be created. User does not have any appointments related to this tutor.")
public class RevisionCreatorException extends RuntimeException{

}

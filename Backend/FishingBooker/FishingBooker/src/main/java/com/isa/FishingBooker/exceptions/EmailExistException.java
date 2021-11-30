package com.isa.FishingBooker.exceptions;

public class EmailExistException extends RegistrationException {
	public EmailExistException() {
		super("Email already exists");
	}

}

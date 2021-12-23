package com.isa.FishingBooker.exceptions;

public class UndefinedEntityClassException extends RuntimeException {

	public UndefinedEntityClassException() {
		super("Entity class undefined.");
	}
}

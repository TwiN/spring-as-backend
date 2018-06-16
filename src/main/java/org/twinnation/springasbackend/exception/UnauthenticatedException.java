package org.twinnation.springasbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UnauthenticatedException extends Exception {
	
	public UnauthenticatedException(String message) {
		super(message);
	}
	
	
	public UnauthenticatedException() {
		super("You must be authenticated to perform this action.");
	}
	
}

package org.twinnation.springasbackend.exception;


public class UnauthenticatedException extends Exception {
	
	public UnauthenticatedException(String message) {
		super(message);
	}
	
	
	public UnauthenticatedException() {
		super("You must be authenticated to perform this action.");
	}
	
}

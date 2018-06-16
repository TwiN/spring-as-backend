package org.twinnation.springasbackend.exception;


public class InvalidUserException extends Exception {
	
	public InvalidUserException(String message) {
		super(message);
	}
	
	
	public InvalidUserException() {
		super("There is no such user");
	}
	
}

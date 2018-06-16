package org.twinnation.springasbackend.exception;


public class UsernameAlreadyInUseException extends Exception {
	
	public UsernameAlreadyInUseException() {
		super("Username already in use.");
	}
	
}

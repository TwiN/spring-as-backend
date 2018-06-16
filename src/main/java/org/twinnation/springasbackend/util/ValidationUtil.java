package org.twinnation.springasbackend.util;

import org.twinnation.springasbackend.exception.ValidationException;


public interface ValidationUtil {
	
	static boolean isPasswordValid(String password) throws ValidationException {
		if (isNullOrEmpty(password)) {
			throw new ValidationException("Password cannot be empty");
		} else if (password.length() < 8) {
			throw new ValidationException("Password should have a length of at least 8");
		}
		return true;
	}
	
	
	static boolean isUsernameValid(String username) throws ValidationException {
		if (isNullOrEmpty(username)) {
			throw new ValidationException("Username cannot be empty");
		} else if (username.length() < 4) {
			throw new ValidationException("Username should have a length of at least 4");
		}
		return true;
	}
	
	
	static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}
	
}

package org.twinnation.springasbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.twinnation.springasbackend.dto.ServerMessage;
import org.twinnation.springasbackend.exception.InvalidUserException;
import org.twinnation.springasbackend.exception.UnauthenticatedException;
import org.twinnation.springasbackend.exception.UsernameAlreadyInUseException;
import org.twinnation.springasbackend.exception.ValidationException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({AccessDeniedException.class})
	public ServerMessage handleAccessDeniedException(Exception e) {
		return new ServerMessage(true, e.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({UnauthenticatedException.class})
	public ServerMessage handleUnauthenticatedException(Exception e) {
		return new ServerMessage(true, e.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({UsernameAlreadyInUseException.class})
	public ServerMessage handleResourceNameAlreadyInUseException(Exception e) {
		return new ServerMessage(true, e.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({InvalidUserException.class, ValidationException.class, Exception.class})
	public ServerMessage handleException(Exception e) {
		return new ServerMessage(true, e.getMessage());
	}
	
}
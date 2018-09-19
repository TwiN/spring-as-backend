package org.twinnation.springasbackend.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.twinnation.springasbackend.dto.ServerMessage;
import org.twinnation.springasbackend.dto.UserDto;
import org.twinnation.springasbackend.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users/me")
	public UserDto getCurrentUser(Authentication authentication) throws Exception {
		return new UserDto(authentication);
	}
	
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDto> getAllUsers() {
		return UserDto.listFromUsers(userService.getAllUsers());
	}
	
	
	@GetMapping("/users/{id}")
	@PreAuthorize("isAuthenticated()")
	public UserDto getUserById(@PathVariable Long id) throws Exception {
		return new UserDto(userService.getUserById(id));
	}
	
	
	@PostMapping("/users")
	@PreAuthorize("!isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	public ServerMessage createUser(@RequestParam String username, @RequestParam String password) throws Exception {
		return userService.createUser(username, password);
	}
	
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ServerMessage deleteUserById(@PathVariable Long id) throws Exception {
		return userService.deleteUserById(id);
	}
	
}

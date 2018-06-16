package org.twinnation.springasbackend.dto;

import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.twinnation.springasbackend.bean.User;
import org.twinnation.springasbackend.exception.InvalidUserException;
import org.twinnation.springasbackend.exception.UnauthenticatedException;

import java.util.ArrayList;
import java.util.List;


public class UserDto {
	
	private Long id;
	private String username;
	private boolean admin;
	
	
	public UserDto(Long id, String username, boolean admin) {
		this.id = id;
		this.username = username;
		this.admin = admin;
	}
	
	
	public UserDto(User user) throws Exception {
		if (user == null) {
			throw new InvalidUserException();
		}
		this.id = user.getId();
		this.username = user.getUsername();
		this.admin = user.isAdmin();
	}
	
	
	public UserDto(Authentication authentication) throws UnauthenticatedException {
		if (authentication == null) {
			throw new UnauthenticatedException();
		}
		User user = (User)authentication.getPrincipal();
		this.id = user.getId();
		this.username = user.getUsername();
		this.admin = user.isAdmin();
	}
	
	
	public Long getId() {
		return id;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public boolean isAdmin() {
		return admin;
	}
	
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	
	public static UserDto fromUser(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.isAdmin());
	}
	
	
	public static List<UserDto> listFromUsers(List<User> users) {
		List<UserDto> userDtos = new ArrayList<>();
		users.forEach(user -> userDtos.add(UserDto.fromUser(user)));
		return userDtos;
	}
	
}

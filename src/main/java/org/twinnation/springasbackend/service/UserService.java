package org.twinnation.springasbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.twinnation.springasbackend.bean.User;
import org.twinnation.springasbackend.dto.ServerMessage;
import org.twinnation.springasbackend.exception.UsernameAlreadyInUseException;
import org.twinnation.springasbackend.exception.ValidationException;
import org.twinnation.springasbackend.repository.UserRepository;
import org.twinnation.springasbackend.util.ValidationUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsernameIgnoreCase(username);
	}
	
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	public ServerMessage createUser(String username, String password) throws ValidationException, UsernameAlreadyInUseException {
		if (ValidationUtil.isUsernameValid(username) && ValidationUtil.isPasswordValid(password)) {
			if (getUserByUsername(username) != null) {
				throw new UsernameAlreadyInUseException();
			}
			userRepository.save(new User(username, passwordEncoder.encode(password)));
		}
		return new ServerMessage(false, "User has been created.");
	}
	
	
	public ServerMessage deleteUserById(Long id) throws Exception {
		User user = getUserById(id);
		if (user == null) {
			throw new Exception("There is no user with that id");
		}
		userRepository.delete(user);
		return new ServerMessage(false, "User '" + user.getUsername() + "' has been deleted.");
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("[loadUserByUsername] username="+username);
		User user = getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("There is no user with the username '"+username+"'.");
		}
		Set<GrantedAuthority> authorities = new HashSet<>();
		if (user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		user.setAuthorities(authorities);
		return user;
	}

}

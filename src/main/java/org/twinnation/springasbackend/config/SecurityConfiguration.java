package org.twinnation.springasbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.twinnation.springasbackend.dto.ServerMessage;
import org.twinnation.springasbackend.dto.UserDto;
import org.twinnation.springasbackend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Profile({"!test", "test-security"}) // don't configure security for test unless it's a security test
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
			.headers()
				.frameOptions().sameOrigin()
				.and()
			.csrf()
				.disable()
			.cors()
				.and()
			.userDetailsService(userService)
			.formLogin()
				.loginProcessingUrl("/api/v1/login")
				.failureHandler(authenticationFailureHandler())
				.successHandler(authenticationSuccessHandler());
	}
	
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
				PrintWriter writer = response.getWriter();
				response.setContentType("application/json");
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				writer.write(new ServerMessage(true, "Invalid username or password").toJson());
			}
		};
	}
	
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
				PrintWriter writer = response.getWriter();
				response.setContentType("application/json");
				response.setStatus(HttpStatus.ACCEPTED.value());
				try {
					writer.write(new UserDto(authentication).toJson());
				} catch (Exception e) { // this shouldn't happen, but we'll relay the exception just in case
					throw new IOException("Couldn't create UserDto out of authentication despite its success");
				}
			}
		};
	}
	
}

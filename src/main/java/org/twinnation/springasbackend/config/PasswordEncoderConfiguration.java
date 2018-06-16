package org.twinnation.springasbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * This needs to be externalized because we can't include SecurityConfiguration while testing,
 * but we still need the PasswordEncoder bean for UserService
 */
@Configuration
public class PasswordEncoderConfiguration {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

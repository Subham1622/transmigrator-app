package com.example.fsetraining.Project1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		
		UserDetails admin = User.builder()
				.username("ADMIN1")
				.password(passwordEncoder.encode("Password1"))
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.builder()
				.username("USER1")
				.password(passwordEncoder.encode("Password2"))
				.roles("USER")
				.build();
		
		UserDetails manager = User.builder()
				.username("MANAGER1")
				.password(passwordEncoder.encode("Password3"))
				.roles("MANAGER")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user, manager);
	}
}

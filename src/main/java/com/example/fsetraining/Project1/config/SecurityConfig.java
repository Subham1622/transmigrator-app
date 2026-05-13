package com.example.fsetraining.Project1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/actuator/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/events/delete/**").hasAnyRole("ADMIN")
				.requestMatchers("/events/**").hasAnyRole("ADMIN","USER","MANAGER")
				.anyRequest().authenticated()
				).formLogin(Customizer.withDefaults());
		
		http.headers(header -> header.frameOptions(frame -> frame.disable()));
		return http.build();
	}
	

}

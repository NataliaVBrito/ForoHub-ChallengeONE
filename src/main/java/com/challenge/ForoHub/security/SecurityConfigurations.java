package com.challenge.ForoHub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.challenge.ForoHub.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private JwtAuthenticationFilter jwtAuth;
	
	@Autowired
	private AuthService authService;
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(authService);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API RESTful sin estado
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                )
                 .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class)
                .build();	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

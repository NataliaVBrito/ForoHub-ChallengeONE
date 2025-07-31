package com.challenge.ForoHub.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEnconderGenerator {

	public static void main(String[] args) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "1234"; 
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Contraseña encriptada para '1234': " + encodedPassword);
		}
}

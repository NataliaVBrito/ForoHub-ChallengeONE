package com.challenge.ForoHub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.challenge.ForoHub.entities.UsuarioEntity;

@Service
public class JWTService {

	@Value("${app.jwt-secret}")
	private String secret;

	public String generarToken(UsuarioEntity usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API ForoHub").withSubject(usuario.getCorreoElectronico())
					.withExpiresAt(fechaExpiracion()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("error al generar el token JWT", exception);
		}
	}

	private Instant fechaExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String validarToken(String token) {
		try {
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("API ForoHub").build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			System.out.println("Error de validación de token: " + exception.getMessage());
			return null; // Token inválido o expirado
		}
	}
}

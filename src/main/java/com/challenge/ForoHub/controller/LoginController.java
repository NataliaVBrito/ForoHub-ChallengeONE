package com.challenge.ForoHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ForoHub.entities.UsuarioEntity;
import com.challenge.ForoHub.service.JWTService;
import com.challenge.ForoHub.utils.AuthDatos;
import com.challenge.ForoHub.utils.TokenJWTDatos;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
	
    @Autowired
    private JWTService service;

	@Autowired
	private AuthenticationManager manager;

	@PostMapping
	public ResponseEntity<?> iniciarSesion(@RequestBody @Valid AuthDatos datos) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correo_electronico(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJWT = service.generarToken((UsuarioEntity) autenticacion.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDatos(tokenJWT));
	}
}

package com.challenge.ForoHub.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.challenge.ForoHub.repository.IUsuarioRepository;
import com.challenge.ForoHub.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Obtener el token del header
		var authHeader = request.getHeader("Authorization");

		if (authHeader != null) {
			var token = authHeader.replace("Bearer ", "");

			var subject = jwtService.validarToken(token); // Necesitas un método validarToken en JWTService

			if (subject != null) {
				// Obtener el usuario de la base de datos
				var usuario = usuarioRepository.findByCorreoElectronico(subject);
				if (usuario != null) {
					// Crear un objeto de autenticación
					var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
							usuario.getAuthorities());
					// Establecer la autenticación en el contexto de seguridad
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}

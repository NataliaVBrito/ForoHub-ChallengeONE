package com.challenge.ForoHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challenge.ForoHub.entities.UsuarioEntity;
import com.challenge.ForoHub.repository.IUsuarioRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = repository.findByCorreoElectronico(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado con correo electrónico: " + username);
		}

		return usuario;
	}
}

package com.challenge.ForoHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ForoHub.entities.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	UsuarioEntity findByCorreoElectronico(String correoElectronico);

	Optional<UsuarioEntity> findById(Long id);
}

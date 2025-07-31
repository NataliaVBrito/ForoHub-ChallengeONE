package com.challenge.ForoHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ForoHub.entities.CursoEntity;
import com.challenge.ForoHub.entities.UsuarioEntity;

@Repository
public interface ICursoRepository extends JpaRepository<CursoEntity, Long>{

	Optional<UsuarioEntity> findById(CursoEntity curso_id);

}

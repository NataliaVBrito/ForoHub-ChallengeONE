package com.challenge.ForoHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ForoHub.entities.TopicoEntity;

@Repository
public interface ITopicoRepository extends JpaRepository<TopicoEntity, Long>{

}

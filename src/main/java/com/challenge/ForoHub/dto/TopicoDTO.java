package com.challenge.ForoHub.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.challenge.ForoHub.entities.RespuestaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	private Boolean status = true;
	private Long usuario_id;
	private Long curso_id;
	private Set<RespuestaEntity> respuestas = new HashSet<>();
}

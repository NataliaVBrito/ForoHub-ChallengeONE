package com.challenge.ForoHub.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String mensaje;
	
	@Column(name = "fecha_creacion",nullable = false)
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@Column(nullable = false)
	private boolean status = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity autor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id", nullable = false)
	private CursoEntity curso;
	
	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)	private Set<RespuestaEntity> respuestas = new HashSet<>();
}
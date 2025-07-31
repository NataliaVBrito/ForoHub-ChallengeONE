package com.challenge.ForoHub.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	
	@Column(nullable = false, unique = true)
	private String titulo;
	
	@Column(nullable = false, columnDefinition = "TEXT", unique = true)
	private String mensaje;
	
	@Column(name = "fecha_creacion",nullable = false)
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@Column(nullable = false)
	private boolean status = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity autor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id", nullable = false)
	private CursoEntity curso;
	
	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)	
	private Set<RespuestaEntity> respuestas = new HashSet<>();
}
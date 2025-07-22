package com.challenge.ForoHub.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(name = "correo_electronico", nullable = false, unique = true)
	private String correoElectronico;
	
	@Column(nullable = false)
	private String contrasena;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuario_perfil", 
        joinColumns = @JoinColumn(name = "usuario_id"), 
        inverseJoinColumns = @JoinColumn(name = "perfil_id"))	
	private Set<PerfilEntity> perfiles = new HashSet<>();
}

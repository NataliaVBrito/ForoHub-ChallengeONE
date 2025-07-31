package com.challenge.ForoHub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String nombreDelRecurso;
	private String nombreDelCampo;
	private Long idDelCampo;
	
	public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, Long idDelCampo) {
		super(String.format("%s No encontrada con %s '%s'", nombreDelRecurso, nombreDelCampo, idDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.idDelCampo = idDelCampo;
	}

	public String getNombreDelRecurso() {
		return nombreDelRecurso;
	}

	public void setNombreDelRecurso(String nombreDelRecurso) {
		this.nombreDelRecurso = nombreDelRecurso;
	}

	public String getNombreDelCampo() {
		return nombreDelCampo;
	}

	public void setNombreDelCampo(String nombreDelCampo) {
		this.nombreDelCampo = nombreDelCampo;
	}

	public long getIdDelCampo() {
		return idDelCampo;
	}

	public void setIdDelCampo(long idDelCampo) {
		this.idDelCampo = idDelCampo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

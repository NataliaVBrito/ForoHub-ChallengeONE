package com.challenge.ForoHub.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class Excepciones {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity getionarError404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity getionarError400(MethodArgumentNotValidException ex) {
		var errores = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
	}

	public record DatosErrorValidacion(String campo, String mensaje) {
		public DatosErrorValidacion(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}

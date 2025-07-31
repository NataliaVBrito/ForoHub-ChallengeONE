package com.challenge.ForoHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ForoHub.dto.TopicoDTO;
import com.challenge.ForoHub.service.TopicoService;
import com.challenge.ForoHub.utils.TopicoResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoService topicoSrv;
	
	@PostMapping("/crear")
	public void crearTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
		topicoSrv.crearTopico(topicoDTO);
	}
	
	@GetMapping
	public TopicoResponse listarPublicaciones(
			@RequestParam(value = "pageNro", defaultValue = "0", required = false) int numPagina,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int cantVisualizacion) {
		return topicoSrv.listarTopicos(numPagina, cantVisualizacion);
	}
	
	@GetMapping("/{id}")
	public TopicoDTO buscarPorId(@PathVariable (name = "id") long id) {
		return topicoSrv.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public TopicoDTO actualizarTopico(@PathVariable (name = "id") long id, @RequestBody @Valid TopicoDTO topicoDTO) {
		return topicoSrv.actualizarTopico(topicoDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public void borrarTopico(@Valid @PathVariable (name = "id") long id) {
		topicoSrv.borrarTopico(id);
	}
}

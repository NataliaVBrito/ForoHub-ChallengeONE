package com.challenge.ForoHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import com.challenge.ForoHub.dto.TopicoDTO;
import com.challenge.ForoHub.entities.CursoEntity;
import com.challenge.ForoHub.entities.TopicoEntity;
import com.challenge.ForoHub.entities.UsuarioEntity;
import com.challenge.ForoHub.exceptions.ResourceNotFoundException;
import com.challenge.ForoHub.repository.ICursoRepository;
import com.challenge.ForoHub.repository.ITopicoRepository;
import com.challenge.ForoHub.repository.IUsuarioRepository;
import com.challenge.ForoHub.utils.TopicoResponse;

import jakarta.transaction.Transactional;

@Service
public class TopicoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ITopicoRepository topicoRepository;

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private ICursoRepository cursoRepository;

	@Transactional
	public TopicoDTO crearTopico(TopicoDTO topicoDTO) {
		UsuarioEntity autor = usuarioRepository.findById(topicoDTO.getUsuario_id())
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", topicoDTO.getUsuario_id()));

		CursoEntity curso = cursoRepository.findById(topicoDTO.getCurso_id())
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", topicoDTO.getCurso_id()));

		TopicoEntity topico = mapearEntity(topicoDTO);

		topico.setAutor(autor);
		topico.setCurso(curso);
		topico.setFechaCreacion(LocalDateTime.now());
		topico.setStatus(topicoDTO.getStatus());

		TopicoEntity nuevoTopico = topicoRepository.save(topico);
		TopicoDTO topicoRespuesta = mapearDTO(nuevoTopico);

		return topicoRespuesta;
	}

	@Transactional
	public TopicoResponse listarTopicos(int numPagina, int pageSize) {
	    Pageable pageable = PageRequest.of(numPagina, pageSize);
	    Page<TopicoEntity> paginacion = topicoRepository.findAll(pageable);

	    List<TopicoEntity> publicaciones = paginacion.getContent();

	    List<TopicoDTO> contenido = publicaciones.stream()
	            .map(publicacion -> mapearDTO(publicacion))
	            .filter(java.util.Objects::nonNull)
	            .collect(Collectors.toList());

	    TopicoResponse response = new TopicoResponse();
	    response.setContenido(contenido);
	    response.setPageNum(numPagina);
	    response.setPageSize(pageSize);
	    response.setTotalElementos(paginacion.getTotalElements());
	    response.setTotalPaginas(paginacion.getTotalPages());
	    response.setUltima(paginacion.isLast());

	    return response;
	}

	public TopicoDTO buscarPorId(long id) {
		TopicoEntity topico = new TopicoEntity();

		if (topicoRepository.existsById(id)) {
			topico = topicoRepository.getById(id);
		}else {
			System.out.println("ID no encontrado");
			return null;
		}

		return mapearDTO(topico);
	}

	public TopicoDTO actualizarTopico(TopicoDTO topicoDTO, long id) {
		TopicoEntity topicoExistente = topicoRepository.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Topico", "id", id)); 
		
		topicoExistente.setTitulo(topicoDTO.getTitulo());
	    topicoExistente.setMensaje(topicoDTO.getMensaje());		

		TopicoEntity topicoActualizado = topicoRepository.saveAndFlush(topicoExistente);

		return mapearDTO(topicoActualizado);
	}

	public void borrarTopico(long id) {
		if (topicoRepository.existsById(id)) {
			topicoRepository.deleteById(id);
		}
	}

	// Mapeo DTO a Entity y de Entity a DTO

	private TopicoDTO mapearDTO(TopicoEntity topicoEntity) {
	    if (topicoEntity == null) {
	        System.out.println("ERRORCITO: topicoEntity es nulo en mapearDTO.");
	        return null; // Retorna null si la entidad es nula
	    }

	    TopicoDTO topico = null;
	    try {
	        topico = modelMapper.map(topicoEntity, TopicoDTO.class);

	        if (topico == null) {
	            System.out.println("ERRORCITO: ModelMapper devolvió un TopicoDTO nulo para la entidad con ID: " + topicoEntity.getId());
	            return null;
	        }

	        // Asigna el ID del autor
	        if (topicoEntity.getAutor() != null) {
	            topico.setUsuario_id(topicoEntity.getAutor().getId());
	        } else {
	            System.out.println("ERRORCITO: topicoEntity.getAutor() es nulo para entidad con ID: " + topicoEntity.getId());
	        }

	        // Asigna el ID del curso
	        if (topicoEntity.getCurso() != null) {
	            topico.setCurso_id(topicoEntity.getCurso().getId());
	        } else {
	            System.out.println("ERRORCITO: topicoEntity.getCurso() es nulo para entidad con ID: " + topicoEntity.getId());
	        }

	        System.out.println("DTO mapeado exitosamente para ID: " + topicoEntity.getId() + ", Titulo: " + topico.getTitulo());

	    } catch (Exception e) {
	        System.err.println("ERRORCITO: Excepción al mapear TopicoEntity a TopicoDTO para entidad con ID: " + topicoEntity.getId() + ". Error: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return topico;
	}

	private TopicoEntity mapearEntity(TopicoDTO topicoDTO) {
		TopicoEntity topico = modelMapper.map(topicoDTO, TopicoEntity.class);

		return topico;
	}
}

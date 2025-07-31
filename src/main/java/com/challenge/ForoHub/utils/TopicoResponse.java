package com.challenge.ForoHub.utils;

import java.util.List;

import com.challenge.ForoHub.dto.TopicoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoResponse {

	private List<TopicoDTO> contenido;
	private int pageNum;
	private int pageSize;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
}

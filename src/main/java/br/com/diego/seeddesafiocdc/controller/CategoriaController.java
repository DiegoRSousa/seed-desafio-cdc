package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CategoriaRequest;
import br.com.diego.seeddesafiocdc.dto.CategoriaResponse;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	private final CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<CategoriaResponse> save(@RequestBody @Valid CategoriaRequest request) {
		var categoria = request.toModel();		
		categoriaRepository.save(categoria);
		return new ResponseEntity<>(new CategoriaResponse(categoria), HttpStatus.CREATED);
	}
}

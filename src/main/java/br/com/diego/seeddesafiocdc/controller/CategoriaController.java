package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CategoriaRequest;
import br.com.diego.seeddesafiocdc.dto.CategoriaResponse;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;

//4
@RestController
@RequestMapping("categorias")
public class CategoriaController {

	//1
	@Autowired
	private CategoriaRepository categoriaRepository;

	//1
	//1
	@PostMapping
	public ResponseEntity<CategoriaResponse> save(@RequestBody @Valid CategoriaRequest request) {
		//1
		var categoria = request.toModel();		
		categoriaRepository.save(categoria);
		return new ResponseEntity<>(new CategoriaResponse(categoria), HttpStatus.CREATED);
	}
}

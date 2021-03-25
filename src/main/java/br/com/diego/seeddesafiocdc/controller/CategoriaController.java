package br.com.diego.seeddesafiocdc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CategoriaRequest;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;

//3
@RestController
@RequestMapping("categorias")
public class CategoriaController {

	//1
	@Autowired
	private CategoriaRepository categoriaRepository;

	//1
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> save(@Valid @RequestBody CategoriaRequest request) {
		//1
		var categoria = request.toModel();		
		categoriaRepository.save(categoria);
		return categoria.toResponse();
	}
}

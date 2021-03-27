package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.AutorRequest;
import br.com.diego.seeddesafiocdc.dto.AutorResponse;
import br.com.diego.seeddesafiocdc.repository.AutorRepository;

//4
@RestController
@RequestMapping("autores")
public class AutorController {

	//1
	@Autowired
	private AutorRepository autorRepository;

	//1
	//1
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AutorResponse> save(@RequestBody @Valid AutorRequest request) {
		//1
		var autor = request.toModel();
		autorRepository.save(autor);
		return new ResponseEntity<>(new AutorResponse(autor), HttpStatus.CREATED);
	}
}

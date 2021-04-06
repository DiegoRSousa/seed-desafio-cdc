package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.PaisRequest;
import br.com.diego.seeddesafiocdc.dto.PaisResponse;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;

@RestController
@RequestMapping("paises")
public class PaisController {

	private PaisRepository paisRepository;
	
	public PaisController(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}

	@PostMapping
	public  ResponseEntity<PaisResponse> save(@RequestBody @Valid PaisRequest request) {
		var pais = request.toModel();
		paisRepository.save(pais);
		return new ResponseEntity<>(new PaisResponse(pais), HttpStatus.CREATED);
	}
}

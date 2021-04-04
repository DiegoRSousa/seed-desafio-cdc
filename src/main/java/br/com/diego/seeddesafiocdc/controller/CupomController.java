package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CupomRequest;
import br.com.diego.seeddesafiocdc.dto.CupomResponse;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;

@RestController
@RequestMapping("cupons")
public class CupomController {

	private final CupomRepository cupomRepository;
	
	public CupomController(CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CupomResponse> save(@RequestBody @Valid CupomRequest request) {
		var cupom = request.toModel();
		cupomRepository.save(cupom);
		return new ResponseEntity<>(new CupomResponse(cupom), HttpStatus.CREATED);
		
	}
}

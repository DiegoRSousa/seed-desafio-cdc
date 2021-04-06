package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.EstadoRequest;
import br.com.diego.seeddesafiocdc.dto.EstadoResponse;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;

@RestController
@RequestMapping("estados")
public class EstadoController {

	private PaisRepository paisRepository;
	private EstadoRepository estadoRepository;
	
	public EstadoController(PaisRepository paisRepository, EstadoRepository estadoRepository) {
		this.paisRepository = paisRepository;
		this.estadoRepository = estadoRepository;
	}


	@PostMapping
	public ResponseEntity<EstadoResponse> save(@RequestBody @Valid  EstadoRequest request) {
		var estado = request.toModel(paisRepository);
		estadoRepository.save(estado);
		return new ResponseEntity<>(new EstadoResponse(estado), HttpStatus.CREATED);		
	}
}
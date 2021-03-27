package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class EstadoController { //6

	@Autowired
	private PaisRepository paisRepository; //1
	@Autowired
	private EstadoRepository estadoRepository; //1
	
	@PostMapping
	public ResponseEntity<EstadoResponse> save(@RequestBody @Valid  EstadoRequest request) { //2
		var pais = paisRepository.getOne(request.getPaisId());  //1
		var estado = request.toModel(pais); //1
		estadoRepository.save(estado);
		return new ResponseEntity<>(new EstadoResponse(estado), HttpStatus.CREATED);		
	}
}
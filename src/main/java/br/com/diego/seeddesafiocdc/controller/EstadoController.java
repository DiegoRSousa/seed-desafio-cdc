package br.com.diego.seeddesafiocdc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.EstadoRequest;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;

@RestController
@RequestMapping("estados")
public class EstadoController { //5

	@Autowired
	private PaisRepository paisRepository; //1
	@Autowired
	private EstadoRepository estadoRepository; //1
	
	@PostMapping
	public Map<String, Object> save(@Valid @RequestBody EstadoRequest request) { //1
		var pais = paisRepository.getOne(request.getPaisId());  //1
		var estado = request.toModel(pais); //1
		estadoRepository.save(estado);
		return estado.toResponse();		
	}
}

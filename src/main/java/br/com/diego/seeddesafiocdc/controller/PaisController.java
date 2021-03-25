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

import br.com.diego.seeddesafiocdc.dto.PaisRequest;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;

//3
@RestController
@RequestMapping("paises")
public class PaisController {

	//1
	@Autowired
	private PaisRepository paisRepository;
	//1
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> save(@Valid @RequestBody PaisRequest request) {
		//1
		var pais = request.toModel();
		paisRepository.save(pais);
		return pais.toResponse();		
	}
}

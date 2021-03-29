package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.validator.EstadoPertenceAPaisValidator;
import br.com.diego.seeddesafiocdc.validator.VerificaDocumentoCpfOuCnpjValidator;

@RestController
@RequestMapping("compras")
public class CompraController {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfOuCnpjValidator(), estadoPertenceAPaisValidator);
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CompraRequest request) {
		return ResponseEntity.ok(request);
	}

}

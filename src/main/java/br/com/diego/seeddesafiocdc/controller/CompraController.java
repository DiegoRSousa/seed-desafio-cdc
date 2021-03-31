package br.com.diego.seeddesafiocdc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.repository.CompraRepository;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;
import br.com.diego.seeddesafiocdc.validator.EstadoPertenceAPaisValidator;
import br.com.diego.seeddesafiocdc.validator.VerificaDocumentoCpfOuCnpjValidator;

@RestController
@RequestMapping("compras")
public class CompraController {
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfOuCnpjValidator(), estadoPertenceAPaisValidator);
	}
	
	@PostMapping
	public Long save(@Valid @RequestBody CompraRequest request) {
		
		var compra = request.toModel(paisRepository, estadoRepository, livroRepository);
		
		compraRepository.save(compra);
		return compra.getId();
	}
}

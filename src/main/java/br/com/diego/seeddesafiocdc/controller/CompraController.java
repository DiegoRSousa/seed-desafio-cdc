package br.com.diego.seeddesafiocdc.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.diego.seeddesafiocdc.dto.CompraDetails;
import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.repository.CompraRepository;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;
import br.com.diego.seeddesafiocdc.validator.CupomValidoValidator;
import br.com.diego.seeddesafiocdc.validator.EstadoPertenceAPaisValidator;
import br.com.diego.seeddesafiocdc.validator.VerificaDocumentoCpfOuCnpjValidator;

@RestController
@RequestMapping("compras")
public class CompraController {
	
	private CompraRepository compraRepository;
	private LivroRepository livroRepository;
	private PaisRepository paisRepository;
	private EstadoRepository estadoRepository;
	private CupomRepository cupomRepository;
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	private CupomValidoValidator cupomValidoValidator;
	
	
	public CompraController(CompraRepository compraRepository, LivroRepository livroRepository,
			PaisRepository paisRepository, EstadoRepository estadoRepository, CupomRepository cupomRepository,
			EstadoPertenceAPaisValidator estadoPertenceAPaisValidator, CupomValidoValidator cupomValidoValidator) {
		this.compraRepository = compraRepository;
		this.livroRepository = livroRepository;
		this.paisRepository = paisRepository;
		this.estadoRepository = estadoRepository;
		this.cupomRepository = cupomRepository;
		this.estadoPertenceAPaisValidator = estadoPertenceAPaisValidator;
		this.cupomValidoValidator = cupomValidoValidator;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfOuCnpjValidator(), estadoPertenceAPaisValidator, cupomValidoValidator);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody CompraRequest request) {
		var compra = request.toModel(paisRepository, estadoRepository, livroRepository, cupomRepository);
		compraRepository.save(compra);
		return ResponseEntity.created(URI.create("compras/" + compra.getId())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraDetails> details(@PathVariable Long id) {
		var compra = compraRepository.findById(id).orElseThrow(
							() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada!"));
		return ResponseEntity.ok(new CompraDetails(compra));
	}
}

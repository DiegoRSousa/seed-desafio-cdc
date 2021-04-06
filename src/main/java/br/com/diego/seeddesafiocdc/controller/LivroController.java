package br.com.diego.seeddesafiocdc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.diego.seeddesafiocdc.dto.LivroRequest;
import br.com.diego.seeddesafiocdc.dto.LivroResponse;
import br.com.diego.seeddesafiocdc.dto.LivroResponseDetails;
import br.com.diego.seeddesafiocdc.repository.AutorRepository;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;

@RestController
@RequestMapping("livros")
public class LivroController {

	private LivroRepository livroRepository;	 
	private AutorRepository autorRepository;
	private CategoriaRepository categoriaRepository;
	
	public LivroController(LivroRepository livroRepository, AutorRepository autorRepository,
			CategoriaRepository categoriaRepository) {
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<LivroResponse> save(@RequestBody @Valid  LivroRequest request) {
		var livro = request.toModel(categoriaRepository, autorRepository);
		livroRepository.save(livro);
		return new ResponseEntity<>(new LivroResponse(livro), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroResponse>> findAll() {
		var livros = livroRepository.findAll().stream().map(LivroResponse::new).collect(Collectors.toList());
		return ResponseEntity.ok(livros);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<LivroResponseDetails> details(@PathVariable Long id) {
		var livro = livroRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!"));
		return ResponseEntity.ok(new LivroResponseDetails(livro));
	}
}
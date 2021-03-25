package br.com.diego.seeddesafiocdc.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.seeddesafiocdc.dto.LivroRequest;
import br.com.diego.seeddesafiocdc.exception.ObjectNotFoundException;
import br.com.diego.seeddesafiocdc.model.Livro;
import br.com.diego.seeddesafiocdc.repository.AutorRepository;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;

//7
@RestController
@RequestMapping("livros")
public class LivroController {

	//1
	@Autowired
	private LivroRepository livroRepository;
	//1
	@Autowired 
	private AutorRepository autorRepository;
	//1
	@Autowired
	private CategoriaRepository categoriaRepository;
	//1
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> save(@Valid @RequestBody LivroRequest request) {
		//1
		var categoria = categoriaRepository.getOne(request.getCategoriaId());
		//1
		var autor = autorRepository.getOne(request.getAutorId());
		//1
		var livro = request.toModel(categoria, autor);
		livroRepository.save(livro);
		return livro.toResponse();
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, Object>> findAll() {
		return livroRepository.findAll().stream().map(Livro::toResponse).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Map<String, Object> details(@PathVariable Long id) {
		var livro = livroRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException(id, Livro.class.getSimpleName()));		
		return livro.toDetailsResponse();
	}
}
package br.com.diego.seeddesafiocdc;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//3
@RestController
@RequestMapping("autores")
public class AutorController {

	//1
	@Autowired
	private AutorRepository autorRepository;

	//1
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Map<String, Object> save(@Valid @RequestBody AutorRequest request) {
		//1
		var autor = request.toModel();
		autorRepository.save(autor);
		return autor.toResponse();
	}
}

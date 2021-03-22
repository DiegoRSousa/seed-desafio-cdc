package br.com.diego.seeddesafiocdc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autores")
public class AutorController {

	//1
	@Autowired
	private AutorRepository autorRepository;
	//1
	//1
	@PostMapping
	public ResponseEntity<AutorResponse> save(@Valid @RequestBody AutorRequest autorRequest) {
		//1
		var autor = autorRequest.toModel();
		autorRepository.save(autor);
		return new ResponseEntity<>(new AutorResponse(autor), HttpStatus.CREATED);
	}
}

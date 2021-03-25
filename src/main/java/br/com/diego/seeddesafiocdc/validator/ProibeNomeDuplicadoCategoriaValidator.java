package br.com.diego.seeddesafiocdc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.diego.seeddesafiocdc.dto.CategoriaRequest;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		var categoriaRequest = (CategoriaRequest) target;
		var possivelCategoria = categoriaRepository.findByNome(categoriaRequest.getNome());
		if(possivelCategoria.isPresent())
			errors.rejectValue("nome", null, "Já existe uma outra categória com o mesmo nome: " + categoriaRequest.getNome());
	}
}
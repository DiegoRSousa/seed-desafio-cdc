package br.com.diego.seeddesafiocdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
			errors.reject("nome", null, "Já existe uma outra categória com o mesmo nome: " + categoriaRequest.getNome());
	}
}
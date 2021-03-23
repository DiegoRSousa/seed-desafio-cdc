package br.com.diego.seeddesafiocdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors())
			return;
		AutorRequest request  = (AutorRequest) target;
		var  possivelAutor = autorRepository.findByEmail(request.getEmail());
		if(possivelAutor.isPresent())
			errors.rejectValue("email", null, "Já existe um outro autor(a) com o mesmo email " + request.getEmail());
	}

}

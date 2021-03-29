package br.com.diego.seeddesafiocdc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;

public class VerificaDocumentoCpfOuCnpjValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		var request = (CompraRequest) target;
		if(!request.documentoValido())
			errors.rejectValue("documento", null, "documento precisar ser cpf cnpj válido!");
	}

}

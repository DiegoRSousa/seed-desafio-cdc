package br.com.diego.seeddesafiocdc.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;

@Component
public class CupomValidate implements Validator {

	private CupomRepository cupomRepository;
	
	public CupomValidate(CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		var request = (CompraRequest) target;
		var possivelCupom = request.getCodigoCupom();
		if(possivelCupom != null) {
			var cupom = cupomRepository.findByCodigo(possivelCupom);
			if(cupom != null && !cupom.isValid())
				errors.rejectValue("codigoCupom", null, "O cupom não é mais válido!");	
		}
	}
}
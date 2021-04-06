package br.com.diego.seeddesafiocdc.validator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		var request = (CompraRequest) target;
		Assert.notNull(request.getPaisId(), "O país e obrigatório!");
		var query = manager.createQuery("select 1 from Estado where pais_id =: value ");
		query.setParameter("value", request.getPaisId().toString());
		if(query.getResultList().size() > 0 && request.getEstadoId() == null)
			errors.rejectValue("estadoId", null, "O país informado contem estado, portando o campo estadoId deve ser preenchido!");
		if(request.getEstadoId() != null) {
			var pais = manager.find(Pais.class, request.getPaisId());
			var estado = manager.find(Estado.class, request.getEstadoId());
			Assert.notNull(pais,"Nao foi encontrado um pais com o id: " + request.getPaisId());
			Assert.notNull(estado,"Nao foi encontrado um estado com o id: " + request.getEstadoId());
			if(estado.getPais().getId() != pais.getId())
				errors.rejectValue("estadoId", null, "O estado não pertence ao país informado!");	
		}
	}
}
package br.com.diego.seeddesafiocdc.errors.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	private String domainAttribute;
	private Class<?> klazz;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(UniqueValue params) {
		domainAttribute = params.fieldName();
		klazz = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		var query = manager.createQuery("select 1 from "+klazz.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um "+klazz.getName()+" com o attributo "+domainAttribute+" = "+value);
		
		return list.isEmpty();
	}
	
	
}

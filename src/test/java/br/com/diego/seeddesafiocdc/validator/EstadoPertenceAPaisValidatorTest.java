package br.com.diego.seeddesafiocdc.validator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.dto.ItemCompraRequest;
import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;

class EstadoPertenceAPaisValidatorTest {

	EntityManager manager = Mockito.mock(EntityManager.class);
	Query query = Mockito.mock(Query.class);
	
	Pais paisComEstado = new Pais("Brasil");
	Estado estado = new Estado("Paraíba", paisComEstado);
	Estado outroEstado = new Estado("Nevado", new Pais("Estados Unidos"));
	Pais paisSemEstado = new Pais("Portugal");
	
	List<ItemCompraRequest> itensCompraRequest = Arrays.asList(new ItemCompraRequest(1L, 1));
	CompraRequest request = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", "528.626.500-64", "Rua Jardim",
			"Quadra 3", "Catolé do Rocha", 1L, 1L, "83999999999", "58884000", new BigDecimal("100"), null, itensCompraRequest);
	CompraRequest requestSemEstado = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", "528.626.500-64", "Rua Jardim",
			"Quadra 3", "Catolé do Rocha", 2L, null, "83999999999", "58884000", new BigDecimal("100"), null, itensCompraRequest);
	
	{
		Mockito.when(query.setParameter("value", "1L")).thenReturn(query);
		Mockito.when(manager.createQuery("select 1 from Estado where pais_id =: value ")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(List.of(paisComEstado));
		
		Mockito.when(manager.find(Pais.class, 1L)).thenReturn(paisComEstado);
		Mockito.when(manager.find(Estado.class, 1L)).thenReturn(estado);
		Mockito.when(manager.find(Pais.class, 2L)).thenReturn(paisSemEstado);
		Mockito.when(manager.find(Estado.class, 2L)).thenReturn(outroEstado);
	}
	
	@Test
	@DisplayName("deve passar caso estado pertenca país")
	void test1() {		
		var errors = new BeanPropertyBindingResult(request, "target");
		var validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deve passar caso request e pais não contenham estado")
	void test2() {
		Mockito.when(manager.createQuery("select 1 from Estado where pais_id =: value ")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
		
		var errors = new BeanPropertyBindingResult(requestSemEstado, "target");
		var validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("não deve passar caso informe um país que contenha estado mas o estado não esteja no request")
	void test3() {
		ReflectionTestUtils.setField(request, "estadoId", null);
		
		var errors = new BeanPropertyBindingResult(request, "target");
		var validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals(1, errors.getErrorCount());
		Assertions.assertEquals("O país informado contem estado, portando o campo estadoId deve ser preenchido!",
								errors.getFieldError("estadoId").getDefaultMessage());
	}
	
	@Test
	@DisplayName("não deve passar caso estado não pertença ao país")
	void test4() {
		ReflectionTestUtils.setField(request, "estadoId", 2L);
		var errors = new BeanPropertyBindingResult(request, "target");
		var validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals(1, errors.getErrorCount());
		Assertions.assertEquals("O estado não pertence ao país informado!", errors.getFieldError("estadoId").getDefaultMessage());
	}

}

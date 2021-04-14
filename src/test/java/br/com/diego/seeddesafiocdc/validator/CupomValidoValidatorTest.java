package br.com.diego.seeddesafiocdc.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;

import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.dto.ItemCompraRequest;
import br.com.diego.seeddesafiocdc.model.Cupom;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;

class CupomValidoValidatorTest {

	Cupom cupom = new Cupom("XXX123", 25, LocalDate.now().plusDays(1));
	private CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
	List<ItemCompraRequest> itensCompraRequest = Arrays.asList(new ItemCompraRequest(1L, 1));
	CompraRequest request = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", "528.626.500-64", "Rua Jardim",
			"Quadra 3", "Catolé do Rocha", 1L, 1L, "83999999999", "58884000", new BigDecimal("100"), "XXX123", itensCompraRequest);
	
	{
		Mockito.when(cupomRepository.findByCodigo("XXX123")).thenReturn(cupom);
	}
	
	@Test
	@DisplayName("deve passar caso cupom esteja valido")
	void test1() {
		var errors = new BeanPropertyBindingResult(request, "target");
		var validator = new CupomValidoValidator(cupomRepository);
		validator.validate(request, errors);
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("não deve passar caso cupom esteja inválido")
	void test2() {
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		
		var errors = new BeanPropertyBindingResult(request, "target");
		var validator = new CupomValidoValidator(cupomRepository);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("O cupom não é mais válido!", errors.getFieldError("codigoCupom").getDefaultMessage());
	}
	
	@Test
	@DisplayName("deve passara caso não tenha cupom")
	void test3() {
		var errors = new BeanPropertyBindingResult(request, "target");
		ReflectionTestUtils.setField(request, "codigoCupom", null);
		var validator = new CupomValidoValidator(cupomRepository);
		validator.validate(request, errors);
		Assertions.assertFalse(errors.hasErrors());
	}

}

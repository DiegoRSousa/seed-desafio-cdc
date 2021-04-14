package br.com.diego.seeddesafiocdc.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class CupomTest {

	@Test
	@DisplayName("deve criar cupom")
	void test1() {
		var cupom = new Cupom("XX123", 25, LocalDate.now().plusDays(1));
		Assertions.assertNotNull(cupom);
		
	}
	
	@Test
	@DisplayName("não deve criar cupom com data passada")
	void test2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Cupom("XX123", 25, LocalDate.now().minusDays(1));
		});
	}
	
	@Test
	@DisplayName("deve retornar true para cupom com data futura")
	void test3() {
		var cupom = new Cupom("XX123", 25, LocalDate.now().plusDays(1));
		Assertions.assertTrue(cupom.isValid());
	}
	
	@Test
	@DisplayName("deve retornar false para cupom com data passada")
	void test4() {
		var cupom = new Cupom("XX123", 25, LocalDate.now().plusDays(1));
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		Assertions.assertFalse(cupom.isValid());
	}
	
	

}

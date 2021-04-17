package br.com.diego.seeddesafiocdc.controller;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.CupomResponse;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CupomControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar cupom")
	void test1() {
		var request = Map.of("codigo", "XXX123", "percentualDeDesconto", "20", "validade", "30/10/2021");
		
		var response = testRestTemplate.postForEntity("/cupons", request, CupomResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertTrue(response.getBody().getId() > 0);
		Assertions.assertEquals("XXX123", response.getBody().getCodigo());
		
	}
	
	@Test
	@DisplayName("não deve salvar cupom quando codigo já existe")
	void test2() {
		var request = Map.of("codigo", "XXX123", "percentualDeDesconto", "20", "validade", "30/10/2021");
		var response = testRestTemplate.postForEntity("/cupons", request, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("codigo", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}

}

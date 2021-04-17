package br.com.diego.seeddesafiocdc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.CategoriaRequest;
import br.com.diego.seeddesafiocdc.dto.CategoriaResponse;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class CategoriaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	CategoriaRequest request = new CategoriaRequest("nome");
	
	@Test
	@DisplayName("deve salvar categoria")
	void test1() {
		var response = testRestTemplate.postForEntity("/categorias", request, CategoriaResponse.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(2L, response.getBody().getId());
	}
	
	@Test
	@DisplayName("não deve salvar categoria quando nome já existe")
	void test2() {
		var response = testRestTemplate.postForEntity("/categorias", request, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("nome", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}
}

package br.com.diego.seeddesafiocdc.controller;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.EstadoResponse;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstadoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar estado")
	void test1() {
		var response = testRestTemplate.postForEntity("/estados", Map.of("nome", "Rio de Janeiro", "paisId", 1L), 
											EstadoResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getNome());
		Assertions.assertTrue(response.getBody().getId() > 0);
	}
	
	@Test
	@DisplayName("não deve salvar estado quando none já existente")
	void test2() {
		var response = testRestTemplate.postForEntity("/estados", Map.of("nome", "Rio de Janeiro", "paisId", 1L), 
				ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("nome", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}
	
	@Test
	@DisplayName("não deve salvar estado quando none pais não existe")
	void test3() {
		var response = testRestTemplate.postForEntity("/estados", Map.of("nome", "São Paulo", "paisId", 10L), 
				ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("paisId", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("não existente", response.getBody().getErrors().get(0).getMessage());
	}

}

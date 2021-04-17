package br.com.diego.seeddesafiocdc.controller;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.PaisResponse;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@AutoConfigureDataJpa
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaisControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar país")
	void test1() {
		var response = testRestTemplate.postForEntity("/paises", Map.of("nome", "Japão"), PaisResponse.class);
		
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getNome());
		Assertions.assertTrue(response.getBody().getId() > 0);
	}
	
	@Test
	@DisplayName("não deve salvar país quando none já existente")
	void test2() {
		var response = testRestTemplate.postForEntity("/paises", Map.of("nome", "Japão"), ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("nome", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}

}

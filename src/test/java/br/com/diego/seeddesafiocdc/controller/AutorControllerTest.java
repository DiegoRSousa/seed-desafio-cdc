package br.com.diego.seeddesafiocdc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import br.com.diego.seeddesafiocdc.dto.AutorRequest;
import br.com.diego.seeddesafiocdc.dto.AutorResponse;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AutorControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	private AutorRequest request;
	
	@Test
	@DisplayName("deve salvar autor")
	void test1() {
		request = new AutorRequest("Teste", "teste@gmail.com", "Desenvolvedor Java");
		var response = testRestTemplate.postForEntity("/autores", request, AutorResponse.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(2L, response.getBody().getId());
	}
	
	@Test
	@DisplayName("não deve salvar autor quando email já existe")
	void test2() {
		request = new AutorRequest("Diego", "diegors90@gmail.com", "Desenvolvedor Java");
		var response = testRestTemplate.postForEntity("/autores", request, ValidationError.class);

		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(1, response.getBody().getErrors().size());
		Assertions.assertEquals("email", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
	}

}

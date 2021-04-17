package br.com.diego.seeddesafiocdc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.LivroRequest;
import br.com.diego.seeddesafiocdc.dto.LivroResponse;
import br.com.diego.seeddesafiocdc.handler.StandardError;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class LivroControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar livro")
	void test1() {
		var request = new LivroRequest("teste", "teste", "sumario", new BigDecimal(100.00), 120, "EBXX100", 
				LocalDate.now().plusMonths(3), 1L, 1L);
		var response = testRestTemplate.postForEntity("/livros", request, LivroResponse.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertTrue(response.getBody().getId() > 0);
	}
	
	@Test
	@DisplayName("não deve salvar livro quando categoria não existente")
	void test2() {
		var request = new LivroRequest("teste2", "teste", "sumario", new BigDecimal(100.00), 120, "EBXX101", 
				LocalDate.now().plusMonths(3), 10L, 1L);
		var response = testRestTemplate.postForEntity("/livros", request, ValidationError.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("categoriaId", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("não existente", response.getBody().getErrors().get(0).getMessage());
		
	}
	
	@Test
	@DisplayName("não deve salvar livro quando autor não existente")
	void test3() {
		var request = new LivroRequest("teste3", "teste", "sumario", new BigDecimal(100.00), 120, "EBXX101", 
				LocalDate.now().plusMonths(3), 1L, 11L);
		var response = testRestTemplate.postForEntity("/livros", request, ValidationError.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("autorId", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("não existente", response.getBody().getErrors().get(0).getMessage());
		
	}
	
	@Test
	@DisplayName("não deve salvar livro quando isbn já existe")
	void test4() {
		var request = new LivroRequest("teste4", "teste", "sumario", new BigDecimal(100.00), 120, "EBXX100", 
				LocalDate.now().plusMonths(3), 1L, 1L);
		var response = testRestTemplate.postForEntity("/livros", request, ValidationError.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("isbn", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
		
	}
	
	@Test
	@DisplayName("não deve salvar livro quando titulo já existe")
	void test5() {
		var request = new LivroRequest("teste", "teste", "sumario", new BigDecimal(100.00), 120, "EBXX101", 
				LocalDate.now().plusMonths(3), 1L, 1L);
		var response = testRestTemplate.postForEntity("/livros", request, ValidationError.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("titulo", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("já existe", response.getBody().getErrors().get(0).getMessage());
		
	}
	
	
	@Test
	@DisplayName("deve retornar todos os livros")
	void test6() {
		var response = testRestTemplate.exchange("/livros", HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<LivroResponse>>(){});
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertTrue(response.getBody().size() > 0);
		Assertions.assertNotNull(response.getBody().get(0).getTitulo());
	}
	
	@Test
	@DisplayName("deve retornar livro quanto buscar detalhes por id")
	void test7() {
		var response = testRestTemplate.exchange("/livros/1", HttpMethod.GET, null, 
				new ParameterizedTypeReference<LivroResponse>(){});
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody().getTitulo());
		Assertions.assertNotNull(response.getBody().getAutor());
	}
	
	@Test
	@DisplayName("não deve retornar livro quanto buscar detalhes por id não existente")
	void test8() {
		var response = testRestTemplate.exchange("/livros/11", HttpMethod.GET, null, 
				new ParameterizedTypeReference<StandardError>(){});
		
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertEquals("Not found", response.getBody().getError());
		Assertions.assertEquals("404 NOT_FOUND \"Livro não encontrado!\"", response.getBody().getMessage());
		
	}
}

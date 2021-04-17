package br.com.diego.seeddesafiocdc.controller;


import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import br.com.diego.seeddesafiocdc.dto.CompraDetails;
import br.com.diego.seeddesafiocdc.dto.CompraRequest;
import br.com.diego.seeddesafiocdc.dto.ItemCompraRequest;
import br.com.diego.seeddesafiocdc.handler.StandardError;
import br.com.diego.seeddesafiocdc.handler.ValidationError;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompraControllerTest {

	private final String URL = "/compras/";
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@DisplayName("deve salvar compra")
	void test1() {
		var itensCompraRequest = Arrays.asList(new ItemCompraRequest(1L, 2), new ItemCompraRequest(2L, 1));
		
		var request = new CompraRequest("teste@gmail.com", "teste", "teste", "528.626.500-64", "teste", "teste", 
				"teste", 1L, 1L, "teste", "58884-000", new BigDecimal("100.00"), null, itensCompraRequest);
		var response = testRestTemplate.postForEntity(URL, request, Object.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertTrue(response.getHeaders().containsKey("Location"));
	}
	
	@Test
	@DisplayName("não deve salvar compra quando cupom inválido")
	void test2() {
		var itensCompraRequest = Arrays.asList(new ItemCompraRequest(1L, 2), new ItemCompraRequest(2L, 1));
		var request = new CompraRequest("teste@gmail.com", "teste", "teste", "528.626.500-64", "teste", "teste", 
				"teste", 1L, 1L, "teste", "58884-000", new BigDecimal("100.00"), "XBS100", itensCompraRequest);
		
		var response = testRestTemplate.postForEntity(URL, request, ValidationError.class);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals("codigoCupom", response.getBody().getErrors().get(0).getFieldName());
		Assertions.assertEquals("O cupom não é mais válido!", response.getBody().getErrors().get(0).getMessage());
	}
	
	@Test
	@DisplayName("deve retornar detalhes da compra")
	void test3() {
		var response = testRestTemplate.exchange(URL + "1", HttpMethod.GET, null, 
				new ParameterizedTypeReference<CompraDetails>() {});
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("deve retornar not found quando compra não existir")
	void test4() {
		var response = testRestTemplate.exchange(URL +"10", HttpMethod.GET, null,
				new ParameterizedTypeReference<StandardError>() {});
		
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		Assertions.assertEquals( "404 NOT_FOUND \"Compra não encontrada!\"", response.getBody().getMessage());
	}

}

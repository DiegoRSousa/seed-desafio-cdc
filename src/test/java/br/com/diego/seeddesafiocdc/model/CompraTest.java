package br.com.diego.seeddesafiocdc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CompraTest {
	
	@ParameterizedTest
	@DisplayName("Verifica se o total do pedido é igual ao valor passado")
	@CsvSource({
		"500.00,true", "499.99,false", "500.01,false"
	})
	void test(BigDecimal valor, boolean resultadoEsperado) {
		var autor = new Autor("Diego", "diegors90@gmail.com", "Desenvolvedor Java");
		var categoria = new Categoria("Engenharia de Software");
		var livro1 = new Livro("titulo", "resumo", "sumario", new BigDecimal("100.00"), 120, "234TXXF", 
									LocalDate.now().plusMonths(5),categoria, autor);
		var livro2 = new Livro("titulo", "resumo", "sumario", new BigDecimal("200.00"), 120, "454TJTF", 
				LocalDate.now().plusMonths(3),categoria, autor);
		var pais = new Pais("Brasil");	
		var estado = new Estado("Paraíba", pais);
		var itensCompra = Arrays.asList(new ItemCompra(livro1, 1), new ItemCompra(livro2, 2));
		
		var compra = new Compra("diegors90@gmail.com", "Diego", "Sousa", "528.626.500-64", "Rua Jardim",
				"Quadra 3", "Catolé do Rocha", pais, estado, "83999999999", "58884000", new BigDecimal("500.00"), 
				itensCompra);
		Assertions.assertEquals(resultadoEsperado, compra.totalIgual(valor));
		
	}

}

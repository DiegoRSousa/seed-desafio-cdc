package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import br.com.diego.seeddesafiocdc.model.Autor;
import br.com.diego.seeddesafiocdc.model.Categoria;
import br.com.diego.seeddesafiocdc.model.Cupom;
import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Livro;
import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;

class CompraRequestTest {
	
	private PaisRepository paisRepository = Mockito.mock(PaisRepository.class); 
	private EstadoRepository estadoRepository = Mockito.mock(EstadoRepository.class); 
	private LivroRepository livroRepository = Mockito.mock(LivroRepository.class);
	private CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
	
	private Autor autor = new Autor("Diego", "diegors90@gmail.com", "Desenvolvedor Java");
	private Categoria categoria = new Categoria("Engenharia de Software");
	private Livro livro = new Livro("titulo", "resumo", "sumario", new BigDecimal("100"), 120, "234TXXF", 
								LocalDate.now().plusMonths(5),categoria, autor);
	private Pais pais = new Pais("Brasil");
	private Estado estado = new Estado("Paraíba", pais);
	private Cupom cupom = new Cupom("XX12", 25, LocalDate.now().plusDays(30));

	private List<ItemCompraRequest> itensCompraRequest = Arrays.asList(new ItemCompraRequest(1L, 1));
	private CompraRequest compraRequest = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", "528.626.500-64", "Rua Jardim",
			"Quadra 3", "Catolé do Rocha", 1L, 1L, "83999999999", "58884000", new BigDecimal("100"), null, itensCompraRequest);
	private CompraRequest compraComCupomRequest = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", 
			"528.626.500-64", "Rua Jardim", "Quadra 3", "Catolé do Rocha", 1L, 1L, "83999999999", "58884000", 
			new BigDecimal("100"), "XX12", itensCompraRequest);
	
	{
		Mockito.when(livroRepository.getOne(1L)).thenReturn(livro);	
		Mockito.when(paisRepository.findById(1L)).thenReturn(Optional.of(pais));
		Mockito.when(estadoRepository.findById(1L)).thenReturn(Optional.of(estado));
		Mockito.when(cupomRepository.findByCodigo("XX12")).thenReturn(cupom);
	}
	
	@Test
	@DisplayName("deve criar uma compra sem cupom")
	void test1() {
		var compra = compraRequest.toModel(paisRepository, estadoRepository, livroRepository, cupomRepository);
		Assertions.assertNotNull(compra);
		Mockito.verify(cupomRepository, Mockito.never()).findByCodigo(Mockito.anyString());
	}
	
	@Test
	@DisplayName("deve criar uma compra com cupom")
	void test2() {
		var compra = compraComCupomRequest.toModel(paisRepository, estadoRepository, livroRepository, cupomRepository);
		Assertions.assertNotNull(compra);
		Mockito.verify(cupomRepository).findByCodigo("XX12");
	}
	
	@ParameterizedTest
	@DisplayName("valida documento")
	@CsvSource({"528.626.500-64,true", "93.894.187/0001-03,true", "528.626.500-61,false"})
	void test3(String documento, boolean resultadoEsperado) {
		var request = new CompraRequest("diegors90@gmail.com", "Diego", "Sousa", documento, "Rua Jardim",
				"Quadra 3", "Catolé do Rocha", 1L, 1L, "83999999999", "58884000", new BigDecimal("100"), null, itensCompraRequest);
		Assertions.assertEquals(resultadoEsperado, request.documentoValido());
	}
	
	
}

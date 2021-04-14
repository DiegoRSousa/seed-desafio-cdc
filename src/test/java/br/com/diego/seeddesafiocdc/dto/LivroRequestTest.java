package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.diego.seeddesafiocdc.model.Autor;
import br.com.diego.seeddesafiocdc.model.Categoria;
import br.com.diego.seeddesafiocdc.repository.AutorRepository;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;

class LivroRequestTest {

	private AutorRepository autorRepository = Mockito.mock(AutorRepository.class);
	private CategoriaRepository categoriaRepository = Mockito.mock(CategoriaRepository.class);
	private LivroRequest request 
				= new LivroRequest("Spring Boot", "", "", BigDecimal.TEN, 120, "123XXX", LocalDate.of(2021,12, 10), 1L, 1L);  
	
	@Test
	@DisplayName("cria livro quanto autor e categoria estão cadastrados")
	void test1() {
		Mockito.when(autorRepository.getOne(1L))
						.thenReturn(new Autor("Diego", "diegors90@gmail.com", "Desenvolvedor Java"));
		Mockito.when(categoriaRepository.getOne(1L))
						.thenReturn(new Categoria("Engenharia de Software"));
		
		Assertions.assertNotNull(request.toModel(categoriaRepository, autorRepository));
	}
	
	@Test
	@DisplayName("não cria o livro caso o autor não exista")
	void test2() {
		Mockito.when(autorRepository.getOne(1L))
			   .thenReturn(null);
		Mockito.when(categoriaRepository.getOne(1L))
			   .thenReturn(new Categoria("Engenharia de Software"));

		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(categoriaRepository, autorRepository);
		});
	}
	
	@Test
	@DisplayName("não cria o livro caso a categoria não exista")
	void test3() {
		Mockito.when(autorRepository.getOne(1L))
			   .thenReturn(new Autor("Diego", "diegors90@gmail.com", "Desenvolvedor Java"));
		Mockito.when(categoriaRepository.getOne(1L))
			   .thenReturn(null);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(categoriaRepository, autorRepository);
		});
	}
	
}

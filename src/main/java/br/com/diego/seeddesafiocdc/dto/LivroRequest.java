package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.diego.seeddesafiocdc.model.Autor;
import br.com.diego.seeddesafiocdc.model.Categoria;
import br.com.diego.seeddesafiocdc.model.Livro;
import br.com.diego.seeddesafiocdc.repository.AutorRepository;
import br.com.diego.seeddesafiocdc.repository.CategoriaRepository;
import br.com.diego.seeddesafiocdc.validator.Exists;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class LivroRequest {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	private String sumario;
	@NotNull
	@DecimalMin("20.00")
	private BigDecimal preco;
	@Min(100)
	private int numeroDePaginas;
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataDePublicacao;
	@NotNull
	@Exists(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;
	@NotNull
	@Exists(domainClass = Autor.class, fieldName = "id")
	private Long autorId;
	
	public Livro toModel(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
		var categoria = categoriaRepository.getOne(categoriaId);
		var autor = autorRepository.getOne(autorId);
		return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataDePublicacao, categoria, autor);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getResumo() {
		return resumo;
	}
	public String getSumario() {
		return sumario;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}
	public String getIsbn() {
		return isbn;
	}
	public LocalDate getDataDePublicacao() {
		return dataDePublicacao;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public Long getAutorId() {
		return autorId;
	}
}

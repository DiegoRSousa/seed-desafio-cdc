package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;

import br.com.diego.seeddesafiocdc.model.Livro;

public class LivroResponseDetails {

	private String titulo;
	private  String resumo;
	private String sumario;
	private BigDecimal preco;
	private int numeroDePaginas;
	private String isbn;
	private String dataDePublicacao;
	private String categoria;
	private String autor;
	
	public LivroResponseDetails(Livro livro) {
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroDePaginas = livro.getNumeroDePaginas();
		this.isbn = livro.getIsbn();
		this.dataDePublicacao = livro.getDataDePublicacaoFormatada("dd/MM/yyyy");
		this.categoria = livro.getCategoria().getNome();
		this.autor = livro.getAutor().getNome();
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

	public String getDataDePublicacao() {
		return dataDePublicacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getAutor() {
		return autor;
	}
}

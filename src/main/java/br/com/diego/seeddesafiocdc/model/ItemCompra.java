package br.com.diego.seeddesafiocdc.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ItemCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Livro livro;
	@NotNull
	@Positive
	private int quantidade;
	@NotNull
	@Positive
	private BigDecimal preco;
	
	@Deprecated
	public ItemCompra() {}
	
	public ItemCompra(Livro livro, @Positive int quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.preco = livro.getPreco();
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Long getId() {
		return id;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}	
}

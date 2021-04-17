package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;

import br.com.diego.seeddesafiocdc.model.Livro;

public class ItemCompraDetails {

	private String titulo;
	private int quantidade;
	private BigDecimal preco;
	private BigDecimal subTotal;
	
	@Deprecated
	public ItemCompraDetails() {}
	
	public ItemCompraDetails(Livro livro, int quantidade) {
		this.titulo = livro.getTitulo();
		this.quantidade = quantidade;
		this.preco = livro.getPreco();
		this.subTotal = livro.getPreco().multiply(new BigDecimal(quantidade));
	}

	public String getTitulo() {
		return titulo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}
}

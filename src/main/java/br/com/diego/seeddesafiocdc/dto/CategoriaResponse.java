package br.com.diego.seeddesafiocdc.dto;

import br.com.diego.seeddesafiocdc.model.Categoria;

public class CategoriaResponse {

	private String nome;
	
	public CategoriaResponse(Categoria categoria) {
		this.nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}
}
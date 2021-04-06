package br.com.diego.seeddesafiocdc.dto;

import br.com.diego.seeddesafiocdc.model.Pais;

public class PaisResponse {

	private String nome;

	public PaisResponse(Pais pais) {
		this.nome = pais.getNome();
	}

	public String getNome() {
		return nome;
	}
}

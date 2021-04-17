package br.com.diego.seeddesafiocdc.dto;

import br.com.diego.seeddesafiocdc.model.Pais;

public class PaisResponse {

	private Long id;
	private String nome;
	
	@Deprecated
	public PaisResponse() {}
	public PaisResponse(Pais pais) {
		this.id = pais.getId();
		this.nome = pais.getNome();
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
}

package br.com.diego.seeddesafiocdc.dto;

import br.com.diego.seeddesafiocdc.model.Estado;

public class EstadoResponse {

	private Long id; 
	private String nome; 
	private String paisNome;
	
	public EstadoResponse(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.paisNome = estado.getPais().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPaisNome() {
		return paisNome;
	}
}

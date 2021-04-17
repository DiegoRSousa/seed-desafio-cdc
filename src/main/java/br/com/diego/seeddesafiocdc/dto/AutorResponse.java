package br.com.diego.seeddesafiocdc.dto;

import java.time.LocalDateTime;

import br.com.diego.seeddesafiocdc.model.Autor;

public class AutorResponse {
	
	private Long id;
	private String nome; 
	private String email;
	private String descricao;
	private LocalDateTime instante;
	
	@Deprecated
	public AutorResponse() {}
	
	public AutorResponse(Autor autor) {
		this.id = autor.getId();
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
		this.instante = autor.getInstante();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
}

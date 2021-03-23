package br.com.diego.seeddesafiocdc;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

	@NotBlank
	private String nome;
	
	public Categoria toModel() {
		return new Categoria(nome);
	}
	
	public String getNome() {
		return nome;
	}
}

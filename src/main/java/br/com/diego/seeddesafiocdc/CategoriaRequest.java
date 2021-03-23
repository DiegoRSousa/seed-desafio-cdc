package br.com.diego.seeddesafiocdc;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	public Categoria toModel() {
		return new Categoria(nome);
	}
	
	public String getNome() {
		return nome;
	}
}

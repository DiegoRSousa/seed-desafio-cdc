package br.com.diego.seeddesafiocdc.dto;

import javax.validation.constraints.NotBlank;

import br.com.diego.seeddesafiocdc.model.Categoria;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

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

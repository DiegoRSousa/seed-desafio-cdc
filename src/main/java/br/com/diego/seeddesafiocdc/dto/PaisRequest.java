package br.com.diego.seeddesafiocdc.dto;

import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class PaisRequest {

	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;

	public Pais toModel() {
		return new Pais(nome);
	}

	public String getNome() {
		return nome;
	}
}

package br.com.diego.seeddesafiocdc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.validator.ExistsId;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class EstadoRequest {

	@NotBlank
	@UniqueValue(domainClass = Estado.class, fieldName = "nome")
	private String nome;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long paisId;
	
	public Estado toModel(Pais pais) {
		return new Estado(nome, pais);
	}

	public String getNome() {
		return nome;
	}
	public Long getPaisId() {
		return paisId;
	} 
}

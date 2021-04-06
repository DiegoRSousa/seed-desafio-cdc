package br.com.diego.seeddesafiocdc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;
import br.com.diego.seeddesafiocdc.validator.Exists;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class EstadoRequest {

	@NotBlank
	@UniqueValue(domainClass = Estado.class, fieldName = "nome")
	private String nome;
	@NotNull
	@Exists(domainClass = Pais.class, fieldName = "id")
	private Long paisId;
	
	public Estado toModel(PaisRepository paisRepository) {
		var pais = paisRepository.getOne(paisId);
		Assert.notNull(pais, "Não foi localizado nenhum país com o id: " + paisId);
		return new Estado(nome, pais);
	}

	public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
		this.nome = nome;
		this.paisId = paisId;
	} 
}

package br.com.diego.seeddesafiocdc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.diego.seeddesafiocdc.model.Autor;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class AutorRequest {

	@NotBlank
	private String nome;
	@Email
	@NotBlank
	@UniqueValue(domainClass = Autor.class, fieldName = "email")
	private String email;
	@Size(min = 1, max = 400)
	private String descricao;
	
	public AutorRequest(@NotBlank String nome, @Email @NotBlank String email,
			@Size(min = 1, max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(nome, email, descricao);
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

}
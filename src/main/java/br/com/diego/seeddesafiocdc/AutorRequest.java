package br.com.diego.seeddesafiocdc;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

	@NotBlank
	private String nome;
	@Email
	@NotBlank
	private String email;
	@Size(min = 1, max = 400)
	private String descricao;
	
	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
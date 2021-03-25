package br.com.diego.seeddesafiocdc.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@Deprecated
	public Categoria() {}
	
	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public Map<String, Object> toResponse() {
		return Map.of("id", id, "nome", nome);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
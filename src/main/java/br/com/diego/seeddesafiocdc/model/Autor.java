package br.com.diego.seeddesafiocdc.model;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	@Size(min = 1, max = 400)
	private String descricao;
	private LocalDateTime instante = LocalDateTime.now();
	
	@Deprecated
	public Autor() {}
	
	public Autor(@NotBlank String nome, @Email @NotBlank String email, @Size(min = 1, max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	public Map<String, Object> toResponse() {
		return Map.of("id", id, "nome", nome, "email", email, "descricao", descricao, "instante", instante);
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
	public LocalDateTime getInstante() {
		return instante;
	}
}
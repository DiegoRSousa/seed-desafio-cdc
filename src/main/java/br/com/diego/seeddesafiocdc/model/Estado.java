package br.com.diego.seeddesafiocdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@ManyToOne
	private Pais pais;
	
	public Estado() {}
	public Estado(@NotBlank String nome, @NotNull Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	public boolean pertenceAPais(Pais pais) {
		return this.pais.equals(pais);		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Long getId() {
		return id;
	}
}

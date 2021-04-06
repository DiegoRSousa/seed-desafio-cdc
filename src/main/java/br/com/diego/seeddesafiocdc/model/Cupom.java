package br.com.diego.seeddesafiocdc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String codigo;
	@NotNull
	@Positive
	private int percentualDeDesconto;
	@Future
	private LocalDate validade;
	
	@Deprecated
	public Cupom() {}

	public Cupom(@NotBlank String codigo, @NotNull @Positive int percentualDeDesconto, @Future LocalDate validade) {
		this.codigo = codigo;
		this.percentualDeDesconto = percentualDeDesconto;
		this.validade = validade;
	}
	
	public boolean isValid() {
		return LocalDate.now().compareTo(validade) <= 0;
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getPercentualDeDesconto() {
		return percentualDeDesconto;
	}

	public LocalDate getValidade() {
		return validade;
	}
}
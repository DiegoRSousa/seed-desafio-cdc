package br.com.diego.seeddesafiocdc.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.diego.seeddesafiocdc.model.Cupom;
import br.com.diego.seeddesafiocdc.validator.UniqueValue;

public class CupomRequest {

	@NotBlank
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;
	@NotNull
	@Positive
	private int percentualDeDesconto;
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	@JsonCreator
	public CupomRequest(@NotBlank String codigo, @NotNull @Positive int percentualDeDesconto,
			@Future LocalDate validade) {
		this.codigo = codigo;
		this.percentualDeDesconto = percentualDeDesconto;
		this.validade = validade;
	}

	public Cupom toModel() {
		return new Cupom(codigo, percentualDeDesconto, validade);
	}
}

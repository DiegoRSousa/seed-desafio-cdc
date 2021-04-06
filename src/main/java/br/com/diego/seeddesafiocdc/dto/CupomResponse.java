package br.com.diego.seeddesafiocdc.dto;

import java.time.LocalDate;

import br.com.diego.seeddesafiocdc.model.Cupom;

public class CupomResponse {

	private Long id;
	private String codigo;
	private int percentual;
	private LocalDate validade;
	
	public CupomResponse(Cupom cupom) {
		this.id = cupom.getId();
		this.codigo = cupom.getCodigo();
		this.percentual = cupom.getPercentualDeDesconto();
		this.validade = cupom.getValidade();
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getPercentual() {
		return percentual;
	}

	public LocalDate getValidade() {
		return validade;
	}
}

package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.validator.ExistsId;

public class CompraRequest {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long paisId;
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long estadoId;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	public boolean documentoValido() {
		Assert.hasLength(documento, "O documento deve ser preenchido");
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public Long getPaisId() {
		return paisId;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}
}

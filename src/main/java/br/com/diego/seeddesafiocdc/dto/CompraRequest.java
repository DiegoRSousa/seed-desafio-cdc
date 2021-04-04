package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.diego.seeddesafiocdc.model.Compra;
import br.com.diego.seeddesafiocdc.model.Estado;
import br.com.diego.seeddesafiocdc.model.Pais;
import br.com.diego.seeddesafiocdc.repository.CupomRepository;
import br.com.diego.seeddesafiocdc.repository.EstadoRepository;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;
import br.com.diego.seeddesafiocdc.repository.PaisRepository;
import br.com.diego.seeddesafiocdc.validator.ExistsId;

public class CompraRequest {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
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
	@NotNull
	@Positive
	private BigDecimal total;
	@Valid
	@Size(min = 1)
	private List<ItemCompraRequest> itensCompraRequest = new ArrayList<>();
	private String codigoCupom;
	
	public boolean documentoValido() {
		Assert.hasLength(documento, "O documento deve ser preenchido");
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}
	
	public Compra toModel(PaisRepository paisRepository, EstadoRepository estadoRepository, 
			LivroRepository livroRepository, CupomRepository cupomRepository) {
		var pais = paisRepository.getOne(paisId);
		var estado = estadoId != null ? estadoRepository.getOne(estadoId) : null;
		var itensCompra = itensCompraRequest.stream().map(item -> item.toModel(livroRepository)).collect(Collectors.toList());
		var compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado, 
				telefone, cep, total, itensCompra);
		if(codigoCupom != null) {
			var cupom = cupomRepository.findByCodigo(codigoCupom);
			Assert.notNull(cupom, "O cupom não existe!");
			compra.setCupom(cupom);
		}
			
		Assert.isTrue(compra.totalIgual(total), "O total do pedido é diferente do total dos itens");
		return compra;

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
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public String getCodigoCupom() {
		return codigoCupom;
	}

	public List<ItemCompraRequest> getItensCompraRequest() {
		return itensCompraRequest;
	}
}

package br.com.diego.seeddesafiocdc.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import br.com.diego.seeddesafiocdc.model.Compra;

public class CompraDetails {
	private String email;
	private String nome;
	private String sobrenome;
	private String documento;
	private String endereco;
	private String complemento;
	private String cidade;
	private String pais;
	private String estado;
	private String telefone;
	private String cep;
	private BigDecimal total;
	private String codigoCupom;
	private int percentual;
	private BigDecimal totalComDesconto;
	private List<ItemCompraDetails> itensCompra = new ArrayList<>();
	
	
	public CompraDetails(Compra compra) {
		Assert.notNull(pais, "O país não pode ser nulo");
		this.email = compra.getEmail();
		this.nome = compra.getNome();
		this.sobrenome = compra.getSobrenome();
		this.documento = compra.getDocumento();
		this.endereco = compra.getEndereco();
		this.complemento = compra.getComplemento();
		this.cidade = compra.getCidade();
		this.pais = compra.getPais().getNome();
		this.estado = compra.getEstado() != null ? compra.getEstado().getNome() : null;
		this.telefone = compra.getTelefone();
		this.cep = compra.getCep();
		this.total = compra.getTotal();
		if (compra.getCupom() != null) {
			this.codigoCupom = compra.getCupom().getCodigo();
			this.percentual = compra.getCupom().getPercentualDeDesconto();
			this.totalComDesconto = this.total.subtract(this.total.multiply(
					new BigDecimal(compra.getCupom().getPercentualDeDesconto())).divide(new BigDecimal("100")));
		}
		
		itensCompra.addAll(compra
							.getItensCompra()
							.stream()
							.map(item -> new ItemCompraDetails(item.getLivro(), item.getQuantidade()))
							.collect(Collectors.toList()));				
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


	public String getPais() {
		return pais;
	}


	public String getEstado() {
		return estado;
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
	
	public int getPercentual() {
		return percentual;
	}
	
	public BigDecimal getTotalComDesconto() {
		return totalComDesconto;
	}


	public List<ItemCompraDetails> getItensCompra() {
		return itensCompra;
	}
	
}

package br.com.diego.seeddesafiocdc.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.diego.seeddesafiocdc.model.ItemCompra;
import br.com.diego.seeddesafiocdc.model.Livro;
import br.com.diego.seeddesafiocdc.repository.LivroRepository;
import br.com.diego.seeddesafiocdc.validator.Exists;

public class ItemCompraRequest {

	@NotNull
	@Exists(domainClass = Livro.class, fieldName = "id")
	private Long livroId;
	@Positive
	private int quantidade;
	
	public ItemCompraRequest(@NotNull Long livroId, @Positive int quantidade) {
		this.livroId = livroId;
		this.quantidade = quantidade;
	}

	public ItemCompra toModel(LivroRepository livroRepository) {
		var livro = livroRepository.getOne(livroId);
		return new ItemCompra(livro, quantidade);
	}
}

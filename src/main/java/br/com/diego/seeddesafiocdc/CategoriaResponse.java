package br.com.diego.seeddesafiocdc;

public class CategoriaResponse {

	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public CategoriaResponse(Categoria categoria) {
		this.nome = categoria.getNome();
	}
}

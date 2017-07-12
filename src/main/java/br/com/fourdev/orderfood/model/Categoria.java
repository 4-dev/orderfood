package br.com.fourdev.orderfood.model;

public enum Categoria {

	BEBIDAS("Bebidas"), 
	PRATOS("Pratos"), 
	SOBREMESAS("Sobremesas");

	private String descricao;

	private Categoria(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}
}

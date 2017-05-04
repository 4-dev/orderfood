package br.com.fourdev.orderfood.model;

public enum StatusMesa {
	DISPONIVEL("Unidade"),
	OCUPADA("Litro");
	
	private String descricao;

	private StatusMesa(String descricao) {
		this.descricao = descricao;

	}
	
}

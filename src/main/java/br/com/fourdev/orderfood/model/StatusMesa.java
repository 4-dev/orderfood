package br.com.fourdev.orderfood.model;

public enum StatusMesa {
	DISPONIVEL("Disponivel"),
	OCUPADA("Ocupada");
	
	private String descricao;

	private StatusMesa(String descricao) {
		this.descricao = descricao;

	}
	
}

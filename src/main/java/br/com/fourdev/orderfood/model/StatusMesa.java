package br.com.fourdev.orderfood.model;

public enum StatusMesa {
	DISPONIVEL("DISPONIVEL"),
	OCUPADA("OCUPADA");
	
	private String descricao;

	private StatusMesa(String descricao) {
		this.descricao = descricao;

	}
	
	public String getDescricao() {
		return descricao;
	}
	
}

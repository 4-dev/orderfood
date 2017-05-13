package br.com.fourdev.orderfood.model;

public enum Unidade {
	
	UN("Unitário"),
	LT("Litro"),
	PC("Pacote"),
	KG("Kilo");
	
	
	private String descricao;

	private Unidade(String descricao) {
		this.descricao = descricao;

	}
	
	public String getDescricao() {
		return descricao;
	}

}

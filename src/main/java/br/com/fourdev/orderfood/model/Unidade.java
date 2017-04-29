package br.com.fourdev.orderfood.model;

public enum Unidade {
	
	UN("Unidade"),
	LT("Litro"),
	PC("Pacote"),
	KG("Kilo");
	
	
	private String descricao;

	private Unidade(String descricao) {
		this.descricao = descricao;

	}
	
	

}

package br.com.fourdev.orderfood.model;

public enum Categoria {

	BEBIDAS("Bebidas"), MASSAS("Massas"), SALADAS("Saladas"), SANDUICHES("Sanduiches"), PIZZAS("Pizzas"), GELADOS(
			"Gelados"), CARNES("Carnes"), PRATOS("Pratos");

	private String descricao;

	private Categoria(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}
}

package br.com.fourdev.orderfood.model;

public enum StatusPedido {
	ABERTO("ABERTO"),
	EMANDAMENTO("EMANDAMENTO"),
	CANCELADO("CANCELADO"),
	FINALIZADO("FINALIZADO");
	
	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;

	}
	public String getDescricao() {
		return descricao;
	}
	
}

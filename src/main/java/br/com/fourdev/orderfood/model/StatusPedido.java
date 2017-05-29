package br.com.fourdev.orderfood.model;

public enum StatusPedido {
	ABERTO("ABERTO"),
	EMANDAMENTO("EmAndamento"),
	CANCELADO("Cancelado"),
	FINALIZADO("Finalizado");
	
	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;

	}
	public String getDescricao() {
		return descricao;
	}
	
}

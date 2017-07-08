package br.com.fourdev.orderfood.dto;

import java.util.List;

public class ObjectSync {

	private List<ProdutoDTO> listProdutos;
	private String mensagem;


	public List<ProdutoDTO> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<ProdutoDTO> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

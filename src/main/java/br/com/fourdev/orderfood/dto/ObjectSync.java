package br.com.fourdev.orderfood.dto;

import java.util.List;

public class ObjectSync {

	private List<ProdutoDTO> listProdutos;
	private EmpresaDTO empresa;
	private String mensagem;
	private int codMesa;

	public List<ProdutoDTO> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<ProdutoDTO> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getCodMesa() {
		return codMesa;
	}

	public void setCodMesa(int codMesa) {
		this.codMesa = codMesa;
	}

}

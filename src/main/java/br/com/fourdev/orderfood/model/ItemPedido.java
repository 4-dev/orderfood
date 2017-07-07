package br.com.fourdev.orderfood.model;

import java.math.BigDecimal;

public class ItemPedido {

	private long numped;
	private long produto;
	
	private String nome;
	
	
	private long quantidade;
	private BigDecimal valorUnitario;
	
	private BigDecimal valorTotal;

	public long getQuantidade() {
		return quantidade;
	}

	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public BigDecimal getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}



	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public long getNumped() {
		return numped;
	}

	public void setNumped(long numped) {
		this.numped = numped;
	}

	public long getProduto() {
		return produto;
	}

	public void setProduto(long produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numped ^ (numped >>> 32));
		result = prime * result + (int) (produto ^ (produto >>> 32));
		result = prime * result + (int) (quantidade ^ (quantidade >>> 32));
		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (numped != other.numped)
			return false;
		if (produto != other.produto)
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (valorUnitario == null) {
			if (other.valorUnitario != null)
				return false;
		} else if (!valorUnitario.equals(other.valorUnitario))
			return false;
		return true;
	}
}

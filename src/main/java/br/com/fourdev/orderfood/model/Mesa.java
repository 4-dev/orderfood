package br.com.fourdev.orderfood.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Mesa {

	private int id;
	private String descricao;
	private List<Pedido> pedidos; // lista pedidos
	private LocalDate horaAberta;
	private LocalDate horaFechada;
	private StatusMesa status;
	private BigDecimal total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getHoraAberta() {
		return horaAberta;
	}

	public void setHoraAberta(LocalDate horaAberta) {
		this.horaAberta = horaAberta;
	}

	public LocalDate getHoraFechada() {
		return horaFechada;
	}

	public void setHoraFechada(LocalDate horaFechada) {
		this.horaFechada = horaFechada;
	}

	public StatusMesa getStatus() {
		return status;
	}

	public void setStatus(StatusMesa status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((horaAberta == null) ? 0 : horaAberta.hashCode());
		result = prime * result + ((horaFechada == null) ? 0 : horaFechada.hashCode());
		result = prime * result + id;
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		Mesa other = (Mesa) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horaAberta == null) {
			if (other.horaAberta != null)
				return false;
		} else if (!horaAberta.equals(other.horaAberta))
			return false;
		if (horaFechada == null) {
			if (other.horaFechada != null)
				return false;
		} else if (!horaFechada.equals(other.horaFechada))
			return false;
		if (id != other.id)
			return false;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		if (status != other.status)
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

}

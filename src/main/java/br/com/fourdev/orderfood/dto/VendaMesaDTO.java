package br.com.fourdev.orderfood.dto;

import java.math.BigDecimal;

public class VendaMesaDTO {
	
	private Integer id;
	
	private BigDecimal total = BigDecimal.ZERO;
	
	public VendaMesaDTO() {
	}

	public VendaMesaDTO(Integer id, BigDecimal total) {
		this.id = id;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}

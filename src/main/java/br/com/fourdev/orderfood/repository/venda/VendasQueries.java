package br.com.fourdev.orderfood.repository.venda;

import java.math.BigDecimal;

public interface VendasQueries {
	
	public BigDecimal valorTotalNoAno();
	
	public BigDecimal valorTotalNoMes();
	
	public Integer totalDePedidos();
	
	public Integer totalDeItens();

}

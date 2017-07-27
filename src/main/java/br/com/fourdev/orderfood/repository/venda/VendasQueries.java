package br.com.fourdev.orderfood.repository.venda;

import java.math.BigDecimal;
import java.util.List;

import br.com.fourdev.orderfood.dto.VendaMesDTO;

public interface VendasQueries {
	
	public BigDecimal valorTotalNoAno();
	
	public BigDecimal valorTotalNoMes();
	
	public Integer totalDePedidos();
	
	public Integer totalDeItens();
	
	public List<VendaMesDTO> totalPorMes();
	
	public BigDecimal valorCincoMeses();

}

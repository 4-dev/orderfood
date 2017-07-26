package br.com.fourdev.orderfood.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.Venda;
import br.com.fourdev.orderfood.repository.venda.Vendas;

@Service
public class VendaService {

	@Autowired
	private Vendas vendas;
	
	public void salvar(int idmesa, List<Pedido> pedidos, Double total) {
		
		Venda venda = new Venda();
		venda.setNumeroMesa(idmesa);
		venda.setValor(new BigDecimal(total));
		venda.setData(Calendar.getInstance());
			vendas.save(venda);
		
	}

	public Venda buscaUltimaVenda() {
		List<Venda> listVendas = vendas.findAll();
		int index = listVendas.size() - 1;
		Venda venda = listVendas.get(index);
	return venda;
	}
	
	
	
}

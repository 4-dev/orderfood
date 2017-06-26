package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.repository.pedido.PedidoRepository;

@Service
public class PedidoService {

	final static Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> pedidosAbertos() {
		StatusPedido statusPedido = StatusPedido.ABERTO;
		return pedidoRepository.retornaStatusPedido(statusPedido);
	}

	public List<Pedido> pedidosEmAndamento() {
		StatusPedido statusPedido = StatusPedido.EMANDAMENTO;
		return pedidoRepository.retornaStatusPedido(statusPedido);
	}
	

	public List<Pedido> pedidosFechados() {
		StatusPedido statusPedido = StatusPedido.FINALIZADO;
		return pedidoRepository.retornaStatusPedido(statusPedido);
	}

	public List<Pedido> pedidosEmCancelados() {
		StatusPedido statusPedido = StatusPedido.CANCELADO;
		return pedidoRepository.retornaStatusPedido(statusPedido);
	}	
	
	public List<Pedido> retornaPedidoPorMesa(int idmesa) {
		return pedidoRepository.retornaPedidoPorMesa(idmesa);
	}

	// public StatusPedido reservarMesa() {

	// return pedidoRepository.selectProdutoList();
	// }

}

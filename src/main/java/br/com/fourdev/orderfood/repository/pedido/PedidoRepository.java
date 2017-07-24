package br.com.fourdev.orderfood.repository.pedido;

import java.util.List;

import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.repository.cliente.Clientes;

public interface PedidoRepository {

	public List<Pedido> selectPedidoList();

	public Pedido selectPedidoPorId(String id);

	public void insertPedido(Pedido pedido);

	public void insertPedido(List<Pedido> pedidos);

	public void updatePedido(Pedido pedido);

	public void deletePedido(String id);

	public List<Pedido> retornaStatusPedido(StatusPedido statusPedido);

	public List<Pedido> retornaPedidoPorMesa(int idmesa, StatusPedido statusPedido);

	public List<ItemPedido> retornaItemPorPedido(int idPedido);
	
	public List<Pedido> retornaPedidoPorCliente(long l, StatusPedido statusPedido);
	
	public void atualizarStatusPedido(Pedido pedido);
	
}

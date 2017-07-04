package br.com.fourdev.orderfood.repository.pedido;

import java.util.List;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;

public interface PedidoRepository {

	public List<Pedido> selectPedidoList();

	public Pedido selectPedidoPorId(String id);

	public void insertPedido(Pedido pedido);
	
	public void insertPedido(List<Pedido> pedidos);

	public void updatePedido(String id, Pedido pedido);

	public void deletePedido(String id);
	
	public List<Pedido> retornaStatusPedido(StatusPedido statusPedido);

	public List<Pedido> retornaPedidoPorMesa(int idmesa);
}

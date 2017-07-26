package br.com.fourdev.orderfood.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.repository.cliente.ClientesQueries;
import br.com.fourdev.orderfood.repository.cliente.Clientes;
import br.com.fourdev.orderfood.repository.pedido.PedidoRepository;

@Service
public class PedidoService {

	final static Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	ClientesQueries clienteRepository;
	
	@Autowired
	private MesaService mesaService;
	
	
	@Autowired
	Clientes clientes;

	
	public void insertPedido(Pedido pedido) {
		pedidoRepository.insertPedido(pedido);
	}

	public String insertPedido(List<Pedido> pedidos) {
		String retorno = "";
		if (!mesaService.mesaSemPedidoFinalizada(pedidos.get(0).getMesa().getIdmesa())) {
			pedidoRepository.insertPedido(pedidos);
			retorno = "OK";
		} else {
			retorno = "Mesa_Finalizada";
		}
		return retorno;
		
	}	

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

	public List<Pedido> retornaPedidoPorMesa(int idmesa, String statusPedido) {
		return pedidoRepository.retornaPedidoPorMesa(idmesa, StatusPedido.valueOf(statusPedido));
	}
	
	public List<ItemPedido> retornaItenPorPedido(int idPedido) {
		return pedidoRepository.retornaItemPorPedido(idPedido);
	}
	
	public List<Pedido> retornaPedidoPorVenda(int idvenda){
		return pedidoRepository.retornaPedidoPorVenda(idvenda);
	}
	
	public List<Pedido> transformarJsonPedido(String pedidoJson) {

		// Gson gson = new Gson();
		String jsonInString = pedidoJson;
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(jsonInString);
		JsonArray jsonArr = jo.getAsJsonArray("listPedidos");
		List<Pedido> pedidos = new ArrayList<Pedido>();
		BigDecimal valorTotalPedido = BigDecimal.ZERO;

		for (int i = 0; i < jsonArr.size(); i++) {
			System.out.println(jsonArr.get(i).getAsJsonObject().get("codigo"));
			System.out.println(jsonArr.get(i).getAsJsonObject().get("dtEmissao").getAsString());

			List<ItemPedido> itens = new ArrayList<ItemPedido>();
			JsonArray jsonItens = jsonArr.get(i).getAsJsonObject().get("itens").getAsJsonArray();
			for (int j = 0; j < jsonItens.size(); j++) {
				int codPedido = jsonItens.get(j).getAsJsonObject().get("codPedido").getAsInt();
				int codProduto = jsonItens.get(j).getAsJsonObject().get("codProduto").getAsInt();
				int quantidade = jsonItens.get(j).getAsJsonObject().get("quantidade").getAsInt();
				BigDecimal valorTotal = jsonItens.get(j).getAsJsonObject().get("valorTotal").getAsBigDecimal();
				BigDecimal valorUnitario = jsonItens.get(j).getAsJsonObject().get("valorUnitario").getAsBigDecimal();

				System.out.println(codPedido);
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setNumped(codPedido);
				itemPedido.setProduto(codProduto);
				itemPedido.setQuantidade(quantidade);
				itemPedido.setValorUnitario(valorUnitario);
				itens.add(itemPedido);
				// Somando total do pedido
				valorTotalPedido = valorTotalPedido.add(valorTotal);
			}

			Pedido pedido = new Pedido();
			
			pedido.setDataCriacao(LocalDateTime.now());
			pedido.setDataEntrega(LocalDateTime.now());
			pedido.setObservacao("");
			pedido.setValorTotal(valorTotalPedido.setScale(2, BigDecimal.ROUND_CEILING));
			pedido.setStatus(StatusPedido.ABERTO);
			// pedido.setUsuario(usuarios.findOne((long) 1));

//			cliente.setIdcliente(1);
//			cliente.setNome("");
//			cliente.setImei(jsonArr.get(i).getAsJsonObject().get("imei").getAsString());

			//Setando o Cliente
			Cliente cliente = new Cliente();
			String imei = jo.get("imei").getAsString();
			cliente = clienteRepository.selectClientePorImei(imei);
			pedido.setCliente(cliente);
			//Setando a Mesa
			Mesa mesa = new Mesa();
			mesa.setIdmesa(jsonArr.get(i).getAsJsonObject().get("codMesa").getAsInt());
			mesa.setDescricao("");
			pedido.setMesa(mesa);

			// Setando os itens
			pedido.setItens(itens);

			pedidos.add(pedido);
		}
		return pedidos;
	}


	// public StatusPedido reservarMesa() {

	// return pedidoRepository.selectProdutoList();
	// }

}

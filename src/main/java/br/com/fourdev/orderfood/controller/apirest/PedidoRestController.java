package br.com.fourdev.orderfood.controller.apirest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.repository.usuario.Usuarios;
import br.com.fourdev.orderfood.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoRestController {

	final static Logger logger = LoggerFactory.getLogger(PedidoRestController.class);
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private Usuarios usuarios;

	// @RequestMapping(value = "/listarPedidos", method = RequestMethod.GET)
	// public List<Pedido> selectPedidoList() {
	// return pedidoService.selectPedidoList();
	// }

	// @RequestMapping(value = "/buscarPedidoPorCodigo/{id}", method =
	// RequestMethod.GET)
	// public Pedido selectPedidoById(@PathVariable("id") String id) {
	// return pedidoService.selectPedidoById(id);
	// }

	@RequestMapping(value = "/gerarpedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertPedido(@RequestBody String pedidoJson) {
		List<Pedido> pedidos = transformarJsonPedido(pedidoJson); 
		if (!pedidos.isEmpty()) {
			try {
				pedidoService.insertPedido(pedidos);	
			} catch (Exception e) {
				return new Gson().toJson("ERRO: "+e.getMessage());
			}
			
			return new Gson().toJson("OK");
		} else {
			return new Gson().toJson("ERRO");
		}
		
	}

	private List<Pedido> transformarJsonPedido(String pedidoJson) {

		// Gson gson = new Gson();
		String jsonInString = pedidoJson;
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject) jsonParser.parse(jsonInString);
		JsonArray jsonArr = jo.getAsJsonArray("listPedidos");
		List<Pedido> pedidos = new ArrayList<Pedido>();

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

				System.out.println(codProduto);
				System.out.println(quantidade);
				System.out.println(valorTotal);
				System.out.println(valorUnitario);
			}

			Pedido pedido = new Pedido();
			Cliente cliente = new Cliente();
			cliente.setIdcliente(1);
			cliente.setNome("admin");
			pedido.setCliente(cliente);
			pedido.setDataCriacao(LocalDateTime.now());
			pedido.setDataEntrega(LocalDateTime.now());
			pedido.setObservacao("");
			pedido.setStatus(StatusPedido.ABERTO);

			// pedido.setUsuario(usuarios.findOne((long) 1));
			Mesa mesa = new Mesa();
			mesa.setDescricao("");
			pedido.setMesa(mesa);

			pedido.setItens(itens);
			// pedido.setNumped(jsonArr.get(i).getAsJsonObject().get("codigo").getAsInt());
			pedidos.add(pedido);
		}
		return pedidos;
	}

	// @RequestMapping(value = "/verificarmesa/{id}", method =
	// RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// public String verificarStatusMesa(@PathVariable("id") int idmesa) throws
	// Exception {
	// Pedido pedido= new Pedido();
	// pedido.setNumped(123);
	// pedido.setCliente(new Cliente());
	// // código que faz o trabalho ;-)
	// Gson gson = new Gson();
	// String valor = "";
	// String userJSONString = gson.toJson(valor);
	//
	// valor = gson.fromJson(userJSONString, Pedido.class);
	// return valor;
	//
	// }
	//
	// @RequestMapping(value = "/atualizarPedido/{id}", method =
	// RequestMethod.PUT)
	// public void updatePedido(@RequestBody Pedido pedido, @PathVariable("id")
	// String id) {
	// logger.debug("id=" + id);
	// pedidoService.updatePedido(id, pedido);
	// }

	// @RequestMapping(value = "/deletarPedido/{id}", method =
	// RequestMethod.DELETE)
	// public void deletePedido(@PathVariable("id") String id) {
	// pedidoService.deletePedido(id);
	// }

	// @RequestMapping(value = "/consultar", method = GET)
	// public Pedido consultar(@RequestParam Integer codigo) {
	// return pedidoService.obterPedido(codigo);
	// }

	// @RequestMapping("/listar")
	// public Iterable<Pedido> listar() {
	// return pedidoService.obterTodos();
	// }

	// @RequestMapping(value = "/novo", method = POST)
	// public Pedido novo(@RequestBody Pedido pedido) {
	// return pedidoService.registrarPedido(pedido);
	// }

	// @RequestMapping(value = "/delete", method = POST)
	// public void delete(@RequestBody Pedido pedido) {
	// pedidoService.delete(pedido.getId());
	// }
}
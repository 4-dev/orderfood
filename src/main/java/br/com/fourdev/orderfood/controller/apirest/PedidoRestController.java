package br.com.fourdev.orderfood.controller.apirest;

import java.math.BigDecimal;
import java.math.MathContext;
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
		List<Pedido> pedidos = pedidoService.transformarJsonPedido(pedidoJson); 
		if (!pedidos.isEmpty()) {
			try {
				pedidoService.insertPedido(pedidos);
				return new Gson().toJson("OK");
			} catch (Exception e) {
				return new Gson().toJson("ERRO: "+e.getMessage());
			}

		} else {
			return new Gson().toJson("ERRO");
		}
		
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
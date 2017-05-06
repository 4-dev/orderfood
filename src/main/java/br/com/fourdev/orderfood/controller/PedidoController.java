//
//package br.com.fourdev.orderfood.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.fourdev.orderfood.model.Pedido;
//import br.com.fourdev.orderfood.service.PedidoService;
//
//@RestController
//@RequestMapping("/pedido")
//public class PedidoController {
//
//	final static Logger logger = LoggerFactory.getLogger(PedidoController.class);
//	@Autowired
//	private PedidoService pedidoService;
//
//	@RequestMapping(value = "/listarPedidos", method = RequestMethod.GET)
//	public List<Pedido> selectPedidoList() {
//		return pedidoService.selectPedidoList();
//	}
//
//	@RequestMapping(value = "/buscarPedidoPorCodigo/{id}", method = RequestMethod.GET)
//	public Pedido selectPedidoById(@PathVariable("id") String id) {
//		return pedidoService.selectPedidoById(id);
//	}
//
//	@RequestMapping(value = "/savarPedido", method = RequestMethod.POST)
//	public void insertPedido(@RequestBody Pedido pedido) {
//
//		logger.debug("id=" + pedido.getId());
//		logger.debug("Name=" + pedido.getNome());
//		logger.debug("age=" + pedido.getDescricao());
//
//		pedidoService.insertPedido(pedido);
//	}
//
//	@RequestMapping(value = "/atualizarPedido/{id}", method = RequestMethod.PUT)
//	public void updatePedido(@RequestBody Pedido pedido, @PathVariable("id") String id) {
//		logger.debug("id=" + id);
//		pedidoService.updatePedido(id, pedido);
//	}
//
//	@RequestMapping(value = "/deletarPedido/{id}", method = RequestMethod.DELETE)
//	public void deletePedido(@PathVariable("id") String id) {
//		pedidoService.deletePedido(id);
//	}
//
//	// @RequestMapping(value = "/consultar", method = GET)
//	// public Pedido consultar(@RequestParam Integer codigo) {
//	// return pedidoService.obterPedido(codigo);
//	// }
//
//	// @RequestMapping("/listar")
//	// public Iterable<Pedido> listar() {
//	// return pedidoService.obterTodos();
//	// }
//
//	// @RequestMapping(value = "/novo", method = POST)
//	// public Pedido novo(@RequestBody Pedido pedido) {
//	// return pedidoService.registrarPedido(pedido);
//	// }
//
//	// @RequestMapping(value = "/delete", method = POST)
//	// public void delete(@RequestBody Pedido pedido) {
//	// pedidoService.delete(pedido.getId());
//	// }
//}
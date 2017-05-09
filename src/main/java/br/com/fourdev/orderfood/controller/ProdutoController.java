
package br.com.fourdev.orderfood.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.service.ProdutoService;

@RestController
public class ProdutoController {

	final static Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "produto/listarProdutos", method = RequestMethod.GET)
	public List<Produto> selectProdutoList() {
		return produtoService.selectProdutoList();
	}

	@RequestMapping(value = "produto/buscarProdutoPorCodigo/{id}", method = RequestMethod.GET)
	public Produto selectProdutoById(@PathVariable("id") String id) {
		return produtoService.selectProdutoById(id);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("produto/cadastro-produto");
		return modelAndView;
	}
	
	@PostMapping("produto/novo")
	public void insertProduto(@RequestBody Produto produto) {

		logger.debug("id=" + produto.getId());
		logger.debug("Name=" + produto.getNome());
		logger.debug("age=" + produto.getDescricao());

		produtoService.insertProduto(produto);
	}

	@RequestMapping(value = "produto/atualizarProduto/{id}", method = RequestMethod.PUT)
	public void updateProduto(@RequestBody Produto produto, @PathVariable("id") String id) {
		logger.debug("id=" + id);
		produtoService.updateProduto(id, produto);
	}

	@RequestMapping(value = "produto/deletarProduto/{id}", method = RequestMethod.DELETE)
	public void deleteProduto(@PathVariable("id") String id) {
		produtoService.deleteProduto(id);
	}

	// @RequestMapping(value = "/consultar", method = GET)
	// public Produto consultar(@RequestParam Integer codigo) {
	// return produtoService.obterProduto(codigo);
	// }

	// @RequestMapping("/listar")
	// public Iterable<Produto> listar() {
	// return produtoService.obterTodos();
	// }

	// @RequestMapping(value = "/novo", method = POST)
	// public Produto novo(@RequestBody Produto produto) {
	// return produtoService.registrarProduto(produto);
	// }

	// @RequestMapping(value = "/delete", method = POST)
	// public void delete(@RequestBody Produto produto) {
	// produtoService.delete(produto.getId());
	// }
}
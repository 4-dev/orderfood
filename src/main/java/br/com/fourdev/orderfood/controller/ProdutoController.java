
package br.com.fourdev.orderfood.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
  
  @Autowired
  private ProdutoService produtoService;
  
//  @RequestMapping(value = "/consultar", method = GET)
//  public Produto consultar(@RequestParam Integer codigo) {
//    return produtoService.obterProduto(codigo);
//  }

  @RequestMapping("/listar")
  public Iterable<Produto> listar() {
    return produtoService.obterTodos();
  }

//  @RequestMapping(value = "/novo", method = POST)
//  public Produto novo(@RequestBody Produto produto) {
//    return produtoService.registrarProduto(produto);
//  }
  
//  @RequestMapping(value = "/delete", method = POST)
//  public void delete(@RequestBody Produto produto) {
//     produtoService.delete(produto.getId());
//  }
}
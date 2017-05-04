package br.com.fourdev.orderfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.repository.Produto.ProdutoRepository;

@Service
public class ProdutoService {

  /**
   * Quantidade máxima de produtos em estoque.
   */
  public static final int QTDE_MAX_PROD_PERECIVEL_ESTOQUE = 30;
  public static final String MSG_FALHA_QTDE_MAX_PROD_PERECIVEL_ESTOQUE = "A quanticade máxima de produtos perecíveis em estoque foi excedida.";

  /**
   * Quantidade grande de produtos em estoque.
   */

  public static final int QTDE_GRANDE_PROD_ESTOQUE = 40;
  /**
   * Quantidade de dias considerados "recentes".
   */
  public static final int QTDE_DIAS_RECENTES = 7;

  @Autowired
  private ProdutoRepository produtoRepository;

//  private void validarProduto(Produto produto) {
//    if (produto.getTipo().equals(Produto.PERECIVEL) && produto.getQuantidade() > QTDE_MAX_PROD_PERECIVEL_ESTOQUE) {
//      throw new RuntimeException(MSG_FALHA_QTDE_MAX_PROD_PERECIVEL_ESTOQUE);
//    }
//  }

//  public Produto registrarProduto(Produto produto) {
//    validarProduto(produto);
//    produto.setData(new Date());
//    return produtoRepository.save(produto);
//  }

//  public Iterable<Produto> obterProdutosRecentes() {
//    Calendar agora = Calendar.getInstance();
//    agora.add(Calendar.DATE, - QTDE_DIAS_RECENTES);
//    return produtoRepository.findByDataGreaterThan(agora.getTime());
//  }
//
//  public Iterable<Produto> obterProdutosEstoqueGrande() {
//    return produtoRepository.findByQuantidadeGreaterThan(QTDE_GRANDE_PROD_ESTOQUE);
//  }
//
//  public Produto obterProduto(Integer codigo) {
//    return produtoRepository.findOneByCodigo(codigo);
//  }

  public Iterable<Produto> obterTodos() {
    return produtoRepository.todos(); //findAll();
  }
  
//  public void delete(Long long1) {
//		produtoRepository.delete(long1);
//  }
}

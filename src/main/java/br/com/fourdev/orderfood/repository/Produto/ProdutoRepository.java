package br.com.fourdev.orderfood.repository.Produto;

import java.util.List;
import java.util.Map;

import br.com.fourdev.orderfood.model.Produto;

public interface ProdutoRepository {

  List<Produto> todos();

  void salvar(Produto produto);

  void alterar(Produto produto);
  
  void delete(Produto produto);

//  Produto comCodigo(int codigo);
//
//  List<Long> codigos();
//
//  List<Map<String, Object>> codigosValores();
//
//  void salvar(List<Produto> produtos);
//
//  long valorTotal();



}

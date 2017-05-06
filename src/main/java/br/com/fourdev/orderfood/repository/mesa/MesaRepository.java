package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import br.com.fourdev.orderfood.model.Produto;

public interface MesaRepository {

	public List<Produto> selectProdutoList();

	public Produto selectProdutoPorId(String id);

	public void insertProduto(Produto produto);

	public void updateProduto(String id, Produto produto);

	public void deleteProduto(String id);

}

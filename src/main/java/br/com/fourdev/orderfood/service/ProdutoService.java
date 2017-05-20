package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.repository.produto.Produtos;

@Service
public class ProdutoService {

	final static Logger logger = LoggerFactory.getLogger(ProdutoService.class);

	@Autowired
	private Produtos produtos;
	
	public List<Produto> selectProdutoList() {
		return produtos.selectProdutoList();
	}

	public Produto selectProdutoById(String id) {
		return produtos.selectProdutoPorId(id);
	}

	public void insertProduto(Produto produto) {
		produtos.insertProduto(produto);
	}

	public void updateProduto(String id, Produto produto) {
		produtos.updateProduto(id, produto);
	}

	public void deleteProduto(String id) {
		produtos.deleteProduto(id);
	}
	
	
	//Implementaçoes para o tymeleaf 
	public void salvar(Produto produto){
		produtos.save(produto);
	}
	
	
	 
	
	
	
	
}

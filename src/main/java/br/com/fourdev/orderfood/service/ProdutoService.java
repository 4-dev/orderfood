package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {

	final static Logger logger = LoggerFactory.getLogger(ProdutoService.class);

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> selectProdutoList() {
		return produtoRepository.selectProdutoList();
	}

	public Produto selectProdutoById(String id) {
		return produtoRepository.selectProdutoPorId(id);
	}

	public void insertProduto(Produto produto) {
		produtoRepository.insertProduto(produto);
	}

	public void updateProduto(String id, Produto produto) {
		produtoRepository.updateProduto(id, produto);
	}

	public void deleteProduto(String id) {
		produtoRepository.deleteProduto(id);
	}
	
}

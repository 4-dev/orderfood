package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.repository.produto.Produtos;
import br.com.fourdev.orderfood.service.exception.ErroAoExcluirProdutoException;

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

	
	
	//Implementaçoes para o tymeleaf 
	public void salvar(Produto produto){
		produtos.save(produto);
	}

	public void delete(Produto produto) {
		
		try {
			produtos.delete(produto);
			produtos.flush();
			
		} catch (Exception e) {
			throw new ErroAoExcluirProdutoException("Impossível apagar produto, já utlilizado em alguma venda!");
		}
		
	}
	
	
	 
	
	
	
	
}

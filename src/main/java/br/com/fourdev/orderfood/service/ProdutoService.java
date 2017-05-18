package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.repository.produto.ProdutoRepository;
import br.com.fourdev.orderfood.repository.produto.Produtos;
import br.com.fourdev.orderfood.storage.FotoStorage;

@Service
public class ProdutoService {

	final static Logger logger = LoggerFactory.getLogger(ProdutoService.class);

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorage fotoStorage;

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
	
	
	//Implementaçoes para o tymeleaf 
	
	@Autowired
	private Produtos produtos;
	 
	
	public void salvar(Produto produto){
		produtos.save(produto);
	}
	
	
	public String salvarFoto(Long id, MultipartFile foto) {
		String nomeFoto = fotoStorage.salvar(foto);
		
		Produto produto = produtos.findOne(id);
		produto.setFoto(nomeFoto);
		produtos.save(produto);
		
		return fotoStorage.getUrl(nomeFoto);
	}
	
}

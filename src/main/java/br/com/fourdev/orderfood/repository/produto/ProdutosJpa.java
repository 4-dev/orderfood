package br.com.fourdev.orderfood.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Produto;

@Repository
public interface ProdutosJpa extends JpaRepository<Produto, Long>, ProdutosQueries{

}

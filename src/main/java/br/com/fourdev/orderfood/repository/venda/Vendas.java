package br.com.fourdev.orderfood.repository.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Venda;

@Repository
public interface Vendas extends JpaRepository<Venda, Integer>, VendasQueries {

	public Venda findById(int id);


}

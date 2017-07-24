package br.com.fourdev.orderfood.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries{
////
}

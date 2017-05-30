package br.com.fourdev.orderfood.repository.cliente;

import java.util.List;

import br.com.fourdev.orderfood.model.Cliente;

public interface ClienteRepository {

	public List<Cliente> selectClienteList();

	public Cliente selectClientePorId(int id);

	public void insertCliente(Cliente Cliente);

	public void updateCliente(String id, Cliente Cliente);

	public void deleteCliente(String id);
	
}

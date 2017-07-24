package br.com.fourdev.orderfood.repository.cliente;

import java.util.List;

import br.com.fourdev.orderfood.model.Cliente;

public interface ClientesQueries {

	public List<Cliente> selectClienteList();

	public Cliente selectClientePorId(int idcliente);

	public Cliente selectClientePorImei(String imei);

	public void insertCliente(Cliente Cliente);

	public void updateCliente(String id, Cliente Cliente);

	public void deleteCliente(String id);
	
}

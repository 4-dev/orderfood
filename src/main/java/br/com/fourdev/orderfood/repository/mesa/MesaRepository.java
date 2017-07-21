package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;

public interface MesaRepository  {


	public List<Mesa> selectMesaList();

	public Mesa selectMesaPorId(int id);

	public void insertMesa(Mesa mesa);

	public boolean updateMesa(Mesa mesa);

	public void deleteMesa(int id);

	public StatusMesa reservarMesa(Mesa mesa);

	public int contaMesas();
	
	public Double totalPorMesa(int idmesa);
	public Double totalPorMesaFinalizada(int idmesa);
	
	// Cliente
	public Cliente existeCliente(String imei);
	
	public boolean existeClienteNaMesa(int idmesa, String imei);

	public void insertCliente(Cliente cliente);
	
	public void insertClienteNaMesa(Cliente cliente, int idmesa);
	
	public void updateClienteNaMesa(Cliente cliente, int idmesa);
}

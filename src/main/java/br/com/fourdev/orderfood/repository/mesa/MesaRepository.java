package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;
import br.com.fourdev.orderfood.model.StatusPedido;

public interface MesaRepository {

	public List<Mesa> selectMesaList();

	public Mesa selectMesaPorId(int id);

	public void insertMesa(Mesa mesa);

	public void updateMesa(String id, Mesa mesa);

	public void deleteMesa(String id);

	public StatusMesa reservarMesa(Mesa mesa);

	public int contaMesas();
	
}

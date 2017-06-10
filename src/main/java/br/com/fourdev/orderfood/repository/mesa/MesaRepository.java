package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;

public interface MesaRepository  {

	public List<Mesa> selectMesaList();

	public Mesa selectMesaPorId(int id);

	public void insertMesa(Mesa mesa);

	public void updateMesa(int id, Mesa mesa);

	public void deleteMesa(int id);

	public StatusMesa reservarMesa(Mesa mesa);

	public int contaMesas();
	
}

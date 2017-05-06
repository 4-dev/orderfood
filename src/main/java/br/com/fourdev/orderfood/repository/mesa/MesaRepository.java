package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import br.com.fourdev.orderfood.model.Mesa;

public interface MesaRepository {

	public List<Mesa> selectMesaList();

	public Mesa selectMesaPorId(String id);

	public void insertMesa(Mesa mesa);

	public void updateMesa(String id, Mesa mesa);

	public void deleteMesa(String id);

}

package br.com.fourdev.orderfood.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.repository.mesa.MesaRepository;

@Service
public class MesaService {

	final static Logger logger = LoggerFactory.getLogger(MesaService.class);

	@Autowired
	private MesaRepository mesaRepository;

	public List<Mesa> selectMesaList() {
		return mesaRepository.selectMesaList();
	}

	public Mesa selectMesaById(String id) {
		return mesaRepository.selectMesaPorId(id);
	}

	public void insertMesa(Mesa mesa) {
		mesaRepository.insertMesa(mesa);
	}

	public void updateMesa(String id, Mesa mesa) {
		mesaRepository.updateMesa(id, mesa);
	}

	public void deleteMesa(String id) {
		mesaRepository.deleteMesa(id);
	}
	
}

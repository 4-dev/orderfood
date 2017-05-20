package br.com.fourdev.orderfood.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import br.com.fourdev.orderfood.config.ClientWebSocketConfig;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;
import br.com.fourdev.orderfood.repository.mesa.MesaRepository;

@Service
public class MesaService {

	final static Logger logger = LoggerFactory.getLogger(MesaService.class);

	@Autowired
	private MesaRepository mesaRepository;

	private StompSession stompSession;

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

	public boolean mesaLiberada(int idmesa) throws InterruptedException, ExecutionException {
		boolean vbliberouMesa = false;
		ClientWebSocketConfig.conectaOuRetornaWebSocket();

		logger.info("Inscrever-se no tópico usando a sessão " + stompSession);
		ClientWebSocketConfig.helloClient.subscribeGreetings(stompSession);

		logger.info("Enviando mensagem" + stompSession);
		ClientWebSocketConfig.helloClient.sendHello(stompSession, "Deyvid - mesa: " + idmesa);
		Thread.sleep(1000);

		return vbliberouMesa;
	}

	public StatusMesa reservarMesa(int idmesa) {
		Mesa mesa = new Mesa();
		StatusMesa statusMesa = null;
		mesa.setStatus(statusMesa.OCUPADA);
		mesa.setId(idmesa);
		mesa.setHoraAberta(LocalDate.now());
		mesa.setHoraFechada(LocalDate.now());
		mesa.setTotal(BigDecimal.ONE);
		mesa.setId(idmesa);
		return mesaRepository.reservarMesa(mesa);
	}

}

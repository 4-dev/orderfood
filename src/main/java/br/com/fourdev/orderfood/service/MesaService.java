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

	public Mesa selectMesaById(int id) {
		return mesaRepository.selectMesaPorId(id);
	}

	public void insertMesa(Mesa mesa) {
		mesaRepository.insertMesa(mesa);
	}

	public void updateMesa(int id, Mesa mesa) {
		mesaRepository.updateMesa(mesa);
	}

	public void deleteMesa(int id) {
		mesaRepository.deleteMesa(id);
	}

	public int contaMesas() {
		return mesaRepository.contaMesas();
	}

	public boolean verificarStatusMesa(int idmesa) throws InterruptedException, ExecutionException {
		boolean vbliberouMesa = false;
		
		Mesa mesa = mesaRepository.selectMesaPorId(idmesa);
		
		if ("LIBERADA".equalsIgnoreCase(mesa.getStatus().toString())) {
			ClientWebSocketConfig cws = null;
			cws.conectaOuRetornaWebSocket();

			logger.info("Inscrever-se no tópico usando a sessão " + cws.stompSession.getSessionId());
			cws.helloClient.subscribeGreetings(cws.stompSession);

			logger.info("Enviando mensagem" + cws.stompSession);
			cws.helloClient.sendHello(cws.stompSession, String.valueOf(mesa.getIdmesa()));
			Thread.sleep(1000);
		
			mesa.setStatus(StatusMesa.OCUPADA.getDescricao());
			mesa.setIdmesa(idmesa);
			mesaRepository.updateMesa(mesa);
			vbliberouMesa = true;			

		}
		return vbliberouMesa;
	}

	public void finalizarMesa(int idmesa){
		Mesa mesa = new Mesa();
		mesa.setIdmesa(idmesa);
		mesa.setStatus(StatusMesa.DISPONIVEL.getDescricao());
		mesaRepository.updateMesa(mesa);
	}
	public StatusMesa reservarMesa(int idmesa) {
		Mesa mesa = new Mesa();
		StatusMesa statusMesa = null;
		mesa.setStatus(statusMesa.OCUPADA.getDescricao());
		mesa.setIdmesa(idmesa);
		mesa.setHoraAberta(LocalDate.now());
		mesa.setHoraFechada(LocalDate.now());
		mesa.setTotal(BigDecimal.ONE);
		mesa.setIdmesa(idmesa);
		return mesaRepository.reservarMesa(mesa);
	}


}

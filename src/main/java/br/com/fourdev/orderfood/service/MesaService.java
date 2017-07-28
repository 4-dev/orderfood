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
import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusMesa;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.model.Venda;
import br.com.fourdev.orderfood.repository.mesa.MesaRepository;
import br.com.fourdev.orderfood.repository.pedido.PedidoRepository;

@Service
public class MesaService {

	final static Logger logger = LoggerFactory.getLogger(MesaService.class);

	@Autowired
	private MesaRepository mesaRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	private StompSession stompSession;


	public boolean mesaComPedidoFinalizada(int idmesa) {
		return mesaRepository.mesaComPedidoFinalizada(idmesa);
	}
	
	public boolean mesaSemPedidoFinalizada(int idmesa) {
		return mesaRepository.mesaSemPedidoFinalizada(idmesa);
	}
	
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

	public boolean existeClienteNaMesa(int idmesa, String imei) {
		return mesaRepository.existeClienteNaMesa(idmesa, imei);
	}

	public void validarClienteNaMesa(int idmesa, String imei) {
		// boolean existe = false;
		Cliente cliente = new Cliente();
		cliente = mesaRepository.existeCliente(imei);

		if ("".equalsIgnoreCase(cliente.getImei())) {
			// cadastrar cliente caso nao exista
			cliente.setNome(imei);
			cliente.setSexo("");
			cliente.setImei(imei);
			mesaRepository.insertCliente(cliente);
			cliente = mesaRepository.existeCliente(imei);
		}

		if (!mesaRepository.existeClienteNaMesa(idmesa, imei)) {
			// verificar se este cliente está em outra mesa e transferir o
			// pedidos de mesa
			mesaRepository.insertClienteNaMesa(cliente, idmesa);

		} else {
			// vincular cliente na mesa
			mesaRepository.updateClienteNaMesa(cliente, idmesa);

//			atualizarPedidoNovaMesa(idmesa, cliente);

		}
		// return existe;
	}

	private void atualizarPedidoNovaMesa(int idmesa, Cliente cliente) {
		Mesa mesa = mesaRepository.selectMesaPorId(idmesa);
		List<Pedido> pedidos = pedidoRepository.retornaPedidoPorCliente(cliente.getIdcliente(), StatusPedido.ABERTO);
		for (Pedido pedido : pedidos) {
			pedido.setCliente(cliente);
			pedido.setMesa(mesa);
			pedidoRepository.updatePedido(pedido);
		}
	}

	public boolean verificarStatusMesa(int idmesa) throws InterruptedException, ExecutionException {
		boolean vbliberouMesa = false;

		Mesa mesa = mesaRepository.selectMesaPorId(idmesa);

		if ("DISPONIVEL".equalsIgnoreCase(mesa.getStatus().toString())) {
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

	public boolean finalizarMesa(int idmesa, List<Pedido> pedidos, Venda venda) {
		Mesa mesa = new Mesa();
//		Double total = totalPorMesa(idmesa);
		mesa.setIdmesa(idmesa);
		mesa.setStatus(StatusMesa.DISPONIVEL.getDescricao());
		boolean atualizou = mesaRepository.updateMesa(mesa);

//		vendaService.salvar(idmesa, pedidos, total);
		System.out.println(pedidos.size());
		if (venda != null) {
			for (Pedido pedido : pedidos) {
				pedido.setVenda(venda);
				pedido.setStatus(StatusPedido.FINALIZADO);
				pedidoRepository.atualizarStatusPedido(pedido);
			}
		}
		

		return atualizou;

	}

	@SuppressWarnings("static-access")
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

	public Double totalPorMesa(int idmesa) {
		Mesa mesa = mesaRepository.selectMesaPorId(idmesa);
		if (mesa.getStatus().equals(StatusMesa.OCUPADA.getDescricao())) {
			return mesaRepository.totalPorMesa(idmesa);
		} else {
			return null;
		}

	}
	
	public Double totalPorMesaFinalizada(int idmesa) {
			return mesaRepository.totalPorMesaFinalizada(idmesa);

	}

}

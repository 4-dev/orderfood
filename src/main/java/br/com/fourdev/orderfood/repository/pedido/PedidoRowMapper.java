package br.com.fourdev.orderfood.repository.pedido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.model.Usuario;
import br.com.fourdev.orderfood.repository.cliente.Clientes;

public class PedidoRowMapper implements RowMapper<Pedido> {
	

//	@Autowired
//	MesaRepository mesaRepository;
	public PedidoRowMapper() {
		super();
	}
//	public PedidoRowMapper(Clientes clientes) {
//		this.clientes = clientes;
//	}
	@Override
	public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {

		Pedido pedido = new Pedido();

		pedido.setNumped(rs.getInt("NUMPED"));
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setValorDesconto(rs.getBigDecimal("valordesconto"));
		pedido.setValorTotal(rs.getBigDecimal("valortotal"));
		pedido.setObservacao(rs.getString("observacao"));
		// pedido.setDataEntrega(LocalDateTime.parse(rs.getString("dataentrega"),
		// DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		// pedido.setDataCancel(LocalDateTime.parse(rs.getString("datacancel"),
		// DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		// pedido.setStatus();
		
		pedido.setStatus(StatusPedido.valueOf(rs.getString("status")));
		Cliente cliente = new Cliente();
		cliente.setIdcliente(rs.getInt("idcliente"));
		pedido.setCliente(cliente);
		Mesa mesa = new Mesa();
		mesa.setIdmesa(rs.getInt("idmesa"));
//		mesa = mesaRepository.selectMesaPorId(rs.getInt("idmesa"));
		pedido.setMesa(mesa);
		
		Usuario usuario = new Usuario();
		pedido.setUsuario(usuario);
		

		return pedido;
	}

}
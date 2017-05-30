package br.com.fourdev.orderfood.repository.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Cliente;

@Repository("ClienteRepository")
public class ClienteRepositoryImpl implements ClienteRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// private static final String INSERT_PRODUTO = "insert into pcprodut (id,
	// nome, descricao) values (?,?,?)";
	// private static final String UPDATE_PRODUTO = "update pcprodut set
	// descricao = ? where codprod = ?";
	// private static final String DELETE_PRODUTO = "delete from pcprodut where
	// codprod = ?";

	public List<Cliente> selectClienteList() {
		String query = "select * from Cliente";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Cliente.class));
	}

	public Cliente selectClientePorId(int idCliente) {
		String query = "select * from cliente where idcliente = ? ";
		Cliente c = (Cliente) jdbcTemplate.queryForObject(query, new Object[] { idCliente }, new BeanPropertyRowMapper(Cliente.class));
		return c;//(Cliente) jdbcTemplate.queryForObject(query, new Object[] { idCliente }, new BeanPropertyRowMapper(Cliente.class));
	}

	public void insertCliente(Cliente Cliente) {

//		String query = "insert into Cliente(id, descricao, pedidos, horaAberta, horaFechada, status, total) "
//				+ " values (?,?,?,?,?,?) ";
//		jdbcTemplate.update(query, new Object[] { Cliente.getIdCliente(), Cliente.getDescricao(), Cliente.getPedidos(),
//				Cliente.getHoraAberta(), Cliente.getHoraAberta(), Cliente.getStatus(), Cliente.getTotal() });
	}

	public void updateCliente(String id, Cliente Cliente) {
//		if (Cliente != null) {
//			String query = "update Cliente set ";
//			query += " descricao = ?, ";
//			query += " horaAberta = ?,";
//			query += " horaFechada = ?,";
//			query += " status = ?";
//			query += " where id = ?";
//			// + "pedidos = ?, "
//			// + "total = ?";
//			jdbcTemplate.update(query, new Object[] { Cliente.getIdCliente(), Cliente.getDescricao(), Cliente.getPedidos(),
//					Cliente.getHoraAberta(), Cliente.getHoraAberta(), Cliente.getStatus(), Cliente.getTotal() });
//		}
	}

	public void deleteCliente(String id) {
//		String query = "delete from Cliente where id=?";
//		jdbcTemplate.update(query, new Object[] { id });
	}



}

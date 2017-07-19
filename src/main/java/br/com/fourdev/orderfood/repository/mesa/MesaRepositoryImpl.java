package br.com.fourdev.orderfood.repository.mesa;

import java.sql.Types;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;

@Repository("mesaRepository")
public class MesaRepositoryImpl implements MesaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EntityManager manager;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// private static final String INSERT_PRODUTO = "insert into pcprodut (id,
	// nome, descricao) values (?,?,?)";
	// private static final String UPDATE_PRODUTO = "update pcprodut set
	// descricao = ? where codprod = ?";
	// private static final String DELETE_PRODUTO = "delete from pcprodut where
	// codprod = ?";

	public List<Mesa> selectMesaList() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "select * from mesa order by idmesa";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Mesa.class));
	}

	public Mesa selectMesaPorId(int idmesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "select * from mesa where idmesa=? ";
		return (Mesa) jdbcTemplate.queryForObject(query, new Object[] { idmesa },
				new BeanPropertyRowMapper(Mesa.class));
	}

	public void insertMesa(Mesa mesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "insert into mesa(descricao, status) " + " values (?,?) ";
		jdbcTemplate.update(query, new Object[] { mesa.getDescricao(), mesa.getStatus() });
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateMesa(Mesa mesa) {
		try {
			boolean atualizou = false;
			String query1 = "set search_path to orderfood, public";
			jdbcTemplate.update(query1, new Object[] {});

			if (mesa != null) {
				String query = "update mesa set ";
				MapSqlParameterSource params = new MapSqlParameterSource();
				if ((!"".equalsIgnoreCase(mesa.getDescricao()) && (mesa.getDescricao() != null))) {
					query += "descricao = :descricao, ";
					params.addValue("descricao", mesa.getDescricao(), Types.VARCHAR);
				}
				// query += " horaAberta = ?,";
				// query += " horaFechada = ?,";
				if ((!"".equalsIgnoreCase(mesa.getStatus()) && (mesa.getStatus() != null))) {
					query += "status = :status";
					params.addValue("status", mesa.getStatus(), Types.VARCHAR);
				}
				query += " where idmesa = :idmesa";
				params.addValue("idmesa", mesa.getIdmesa(), Types.INTEGER);

				// try {
				namedParameterJdbcTemplate.update(query, params);
				// } catch (Exception e) {
				// System.out.println(e.getCause());
				// }
				return atualizou;

			} else {
				return atualizou;
			}
		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteMesa(int id) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "delete from mesa where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

	@Override
	public StatusMesa reservarMesa(Mesa mesa) {
		StatusMesa statusmesa = StatusMesa.DISPONIVEL;
		updateMesa(mesa);
		return statusmesa;
	}

	public int contaMesas() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "select count(idmesa) from mesa";
		int contaMesas = (Integer) this.jdbcTemplate.queryForObject(query, Integer.class);
		return contaMesas;
	}

	@Override
	public Double totalPorMesa(int idmesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT sum(cab.valortotal) FROM mesa_pedido mp, cabpedido cab WHERE mp.numped = cab.numped AND cab.status LIKE 'ABERTO'AND mp.idmesa = ?";
		Double total = this.jdbcTemplate.queryForObject(query, new Object[] { idmesa }, Double.class);
		return total;
	}

	@Override
	public Cliente existeCliente(String imei) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT count(idcliente) FROM cliente WHERE imei = ?";
		int idcliente = this.jdbcTemplate.queryForObject(query, new Object[] { imei }, Integer.class);

		Cliente cliente = new Cliente();
		if (idcliente > 0) {
			query = "SELECT * FROM cliente WHERE imei = ?";
			cliente = (Cliente) this.jdbcTemplate.queryForObject(query, new Object[] { imei }, new BeanPropertyRowMapper(Cliente.class));
		} else {
			cliente = null;
		}
		
		return cliente;
	}

	@Override
	public boolean existeClienteNaMesa(int idmesa, String imei) {

		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT COUNT(CM.idcliente)IDCLIENTE " + " FROM cliente_mesa cm, cliente c  "
				+ " WHERE c.idcliente = cm.idcliente " + "   AND c.imei = ?"  //" + "   AND cm.idmesa = ?"
				+ "   AND to_char(CM.DATA, 'DD-MM-yyyy')  = to_char(CURRENT_DATE, 'DD-MM-yyyy')";
		int idcliente = this.jdbcTemplate.queryForObject(query, new Object[] { imei }, Integer.class);
		return idcliente > 0;
	}

	@Override
	public void insertCliente(Cliente cliente) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "insert into cliente(IDCLIENTE, NOME, SEXO, IMEI) "
				+ " values ((select Max(IDCLIENTE) + 1 FROM CLIENTE ), ?, ?, ?) ";
		jdbcTemplate.update(query, new Object[] { cliente.getNome(), cliente.getSexo(), cliente.getImei() });

	}

	@Override
	public void insertClienteNaMesa(Cliente cliente, int idmesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "insert into cliente_mesa(idcliente, idmesa, data) " + " values (?, ?, CURRENT_DATE) ";
		jdbcTemplate.update(query, new Object[] { cliente.getIdcliente(), idmesa });

	}

	@Override
	public void updateClienteNaMesa(Cliente cliente, int idmesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "update CLIENTE_MESA set ";
		MapSqlParameterSource params = new MapSqlParameterSource();

		query += " idmesa = :idmesa ";
		params.addValue("idmesa", idmesa, Types.INTEGER);

		query += " WHERE IDCLIENTE = (SELECT IDCLIENTE FROM CLIENTE WHERE IMEI = :IMEI)"
				+ " AND to_char(DATA, 'DD-MM-yyyy') = to_char(CURRENT_DATE, 'DD-MM-yyyy') ";
		params.addValue("IMEI", cliente.getImei(), Types.VARCHAR);

		namedParameterJdbcTemplate.update(query, params);

	}
}

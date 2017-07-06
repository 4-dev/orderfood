package br.com.fourdev.orderfood.repository.mesa;

import java.sql.Types;
import java.util.List;

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

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;

@Repository("mesaRepository")
public class MesaRepositoryImpl implements MesaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	public void updateMesa(Mesa mesa) {
		try {

			String query1 = "set search_path to orderfood, public";
			jdbcTemplate.update(query1, new Object[] {});
			if (mesa != null) {
				String query = "update mesa set ";
				MapSqlParameterSource params = new MapSqlParameterSource();
				if ((!"".equalsIgnoreCase(mesa.getDescricao()) && (mesa.getDescricao() != null))) {
					query += "descricao = :descricao, ";
					params.addValue("descricao", mesa.getDescricao(),Types.VARCHAR);
				}
				// query += " horaAberta = ?,";
				// query += " horaFechada = ?,";
				if ((!"".equalsIgnoreCase(mesa.getStatus()) && (mesa.getStatus() != null))) {
					query += "status = :status";
					params.addValue("status", mesa.getStatus(), Types.VARCHAR);
				}
				query += " where idmesa = :idmesa";
				params.addValue("idmesa", mesa.getIdmesa(), Types.INTEGER);

//				try {
					namedParameterJdbcTemplate.update(query, params);
//				} catch (Exception e) {
//					System.out.println(e.getCause());
//				}
				
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

		String query = "select count(1) from mesa";
		int contaMesas = (Integer) this.jdbcTemplate.queryForObject(query, Integer.class);
		return contaMesas;
	}

}

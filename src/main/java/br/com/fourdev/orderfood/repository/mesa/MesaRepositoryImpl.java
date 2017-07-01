package br.com.fourdev.orderfood.repository.mesa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.StatusMesa;

@Repository("mesaRepository")
public class MesaRepositoryImpl implements MesaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// private static final String INSERT_PRODUTO = "insert into pcprodut (id,
	// nome, descricao) values (?,?,?)";
	// private static final String UPDATE_PRODUTO = "update pcprodut set
	// descricao = ? where codprod = ?";
	// private static final String DELETE_PRODUTO = "delete from pcprodut where
	// codprod = ?";

	public List<Mesa> selectMesaList() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "select * from mesa order by idmesa";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Mesa.class));
	}

	public Mesa selectMesaPorId(int idmesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "select * from mesa where idmesa=? ";
		return (Mesa) jdbcTemplate.queryForObject(query, new Object[] { idmesa }, new BeanPropertyRowMapper(Mesa.class));
	}

	public void insertMesa(Mesa mesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "insert into mesa(descricao, status) "
				+ " values (?,?) ";
		jdbcTemplate.update(query, new Object[] { mesa.getDescricao(),
				mesa.getStatus() });
	}

	public void updateMesa(Mesa mesa) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		if (mesa != null) {
			String query = "update mesa set ";
			query += " descricao = ?, ";
//			query += " horaAberta = ?,";
//			query += " horaFechada = ?,";
			query += " status = ?";
			query += " where idmesa = ?";
			// + "pedidos = ?, "
			// + "total = ?";
			jdbcTemplate.update(query, new Object[] {mesa.getDescricao(), mesa.getStatus(), mesa.getIdmesa() });
		}
	}

	public void deleteMesa(int id) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
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
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "select count(1) from mesa";
		int contaMesas = (Integer) this.jdbcTemplate.queryForObject(query, Integer.class);
		return contaMesas;
	}


}

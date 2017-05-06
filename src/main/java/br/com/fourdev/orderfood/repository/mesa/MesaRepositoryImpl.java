package br.com.fourdev.orderfood.repository.mesa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Categoria;
import br.com.fourdev.orderfood.model.Mesa;

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
		String query = "select * from mesa";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Mesa.class));
	}

	public Mesa selectMesaPorId(String id) {
		String query = "select * from mesa where id=? ";
		return (Mesa) jdbcTemplate.queryForObject(query, new Object[] { id }, new BeanPropertyRowMapper(Mesa.class));
	}

	public void insertMesa(Mesa mesa) {

		String query = "insert into mesa(id, descricao, pedidos, horaAberta, horaFechada, status, total) "
				+ " values (?,?,?,?,?,?) ";
		jdbcTemplate.update(query, new Object[] { mesa.getId(), mesa.getDescricao(), mesa.getPedidos(),
				mesa.getHoraAberta(), mesa.getHoraAberta(), mesa.getStatus(), mesa.getTotal() });
	}

	public void updateMesa(String id, Mesa mesa) {
		String query = "update mesa set id = ?, descricao = ?, pedidos = ?, horaAberta = ?, horaFechada = ?, status = ?, total = ?";
		jdbcTemplate.update(query, new Object[] { mesa.getId(), mesa.getDescricao(), mesa.getPedidos(),
				mesa.getHoraAberta(), mesa.getHoraAberta(), mesa.getStatus(), mesa.getTotal() });
	}

	public void deleteMesa(String id) {
		String query = "delete from mesa where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

}

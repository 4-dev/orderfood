package br.com.fourdev.orderfood.repository.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.fourdev.orderfood.dto.VendaMesDTO;
import br.com.fourdev.orderfood.repository.pedido.ItemPedidoRowMapper;

public class VendasImpl implements VendasQueries{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public BigDecimal valorTotalNoAno() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "SELECT sum(ven.valor) FROM orderfood.venda ven WHERE Extract('Year' From data) = Extract('Year' From Now())";
		BigDecimal total = this.jdbcTemplate.queryForObject(query, new Object[] {}, BigDecimal.class);
		return total;
	}

	@Override
	public BigDecimal valorTotalNoMes() {
		String query = "SELECT sum(ven.valor) FROM orderfood.venda ven WHERE Extract('Month' From data) = Extract('Month' From Now());";
		BigDecimal total = this.jdbcTemplate.queryForObject(query, new Object[] {}, BigDecimal.class);
		return total;
	}

	@Override
	public Integer totalDePedidos() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});

		String query = "SELECT count(*) FROM orderfood.cabpedido";
		return this.jdbcTemplate.queryForObject(query, new Object[] {}, Integer.class);
	
	}

	@Override
	public Integer totalDeItens() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});

		String query = "SELECT count(*) FROM orderfood.produto";
		return this.jdbcTemplate.queryForObject(query, new Object[] {}, Integer.class);
	}

	@Override
	public List<VendaMesDTO> totalPorMes() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT TO_CHAR(data, 'YYYY/MM') mes, "
				+ "COUNT(*) total FROM orderfood.venda "
				+ "WHERE data > (NOW() - INTERVAL '5 MONTH') "
				+ "GROUP BY mes "
				+ "ORDER BY mes desc";
		return jdbcTemplate.query(query, new Object[] {}, new VendaMesRowMapper());
		
	}

	@Override
	public BigDecimal valorCincoMeses() {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "SELECT sum(valor) total FROM orderfood.venda WHERE data > (NOW() - INTERVAL '5 MONTH')";
		BigDecimal total = this.jdbcTemplate.queryForObject(query, new Object[] {}, BigDecimal.class);
		return total;
	}

}

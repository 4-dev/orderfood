package br.com.fourdev.orderfood.repository.venda;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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

}

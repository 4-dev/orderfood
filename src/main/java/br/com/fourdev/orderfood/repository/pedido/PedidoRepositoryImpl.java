package br.com.fourdev.orderfood.repository.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;

@Repository("PedidoRepository")
public class PedidoRepositoryImpl implements PedidoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Pedido> selectPedidoList() {

		try {

			String query = "select * from cabpedido cab " + "INNER JOIN  itempedido item ON (cab.numped = item.numped)";
			return jdbcTemplate.query(query, new BeanPropertyRowMapper(Pedido.class));

		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Pedido> retornaStatusPedido(StatusPedido statusPedido) {

		try {

			String query = "select * from cabpedido cab where status = ?";
			return jdbcTemplate.query(query, new Object[] { statusPedido.getDescricao() }, new BeanPropertyRowMapper(Pedido.class));

		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public Pedido selectPedidoPorId(String numped) {
		String query = "select * from Pedido where id=? ";
		return (Pedido) jdbcTemplate.queryForObject(query, new Object[] { numped },
				new BeanPropertyRowMapper(Pedido.class));
	}

	public void insertPedido(Pedido pedido) {
		try {

			for (ItemPedido item : pedido.getItens()) {
				String query = "insert into itempedido(numped, produto, quantidade, valorUnitario) "
						+ " values (?,?,?,?) ";
				jdbcTemplate.update(query, new Object[] { item.getNumped(), item.getProduto(), item.getQuantidade(),
						item.getValorUnitario() });
			}

			String query = "insert into cabpedido(numped, dataCriacao, valorDesconto, "
					+ "	valorTotal, observacao, dataEntrega, status) " + " values (?,?,?,?,?,?,?) ";
			jdbcTemplate.update(query,
					new Object[] { pedido.getNumped(), pedido.getDataCriacao(), pedido.getValorDesconto(),
							pedido.getValorTotal(), pedido.getObservacao(), pedido.getDataEntrega(),
							pedido.getStatus() });

		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void updatePedido(String id, Pedido pedido) {
		// String query = "update Pedido set nome=?, descricao=? where id=? ";
		// jdbcTemplate.update(query,
		// new Object[] { Pedido.getId(), Pedido.getNome(),
		// Pedido.getDescricao(), Pedido.getUrlFoto(),
		// Pedido.getVolume(), Pedido.getValor(), Pedido.getQuantidadeEstoque(),
		// Pedido.getCategoria().getDescricao() });
	}

	public void deletePedido(String id) {
		String query = "delete from Pedido where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

}

package br.com.fourdev.orderfood.repository.pedido;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.model.ItemPedido;

public class ItemPedidoRowMapper implements RowMapper<ItemPedido> {

	@Override
	public ItemPedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemPedido item = new ItemPedido();
		
		item.setNumped(rs.getLong("numped"));
		item.setProduto(rs.getLong("id"));
		item.setNome(rs.getString("nome"));
		item.setQuantidade(rs.getLong("quantidade"));
		item.setValorUnitario(rs.getBigDecimal("valor"));
		
		BigDecimal valor = new BigDecimal(item.getQuantidade());
		
		item.setValorTotal(valor.multiply(item.getValorUnitario()));
		
		return item;
	}

}

package br.com.fourdev.orderfood.repository.pedido;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Produto;

public class ItemPedidoRowMapper implements RowMapper<ItemPedido> {

	@Override
	public ItemPedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemPedido item = new ItemPedido();
		Produto produto = new Produto();
		
		item.setProduto(rs.getLong("id"));
		
		

		return item;
	}

}

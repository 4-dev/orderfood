package br.com.fourdev.orderfood.repository.produto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.fourdev.orderfood.model.Produto;

final class ProdutoRowMapper implements RowMapper<Produto> {

  @Override
  public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
    Produto produto = new Produto();
    produto.setId(rs.getLong("id"));
    produto.setNome(rs.getString("nome"));
    produto.setDescricao(rs.getString("descricao"));
//    produto.setCategoria(rs.getString("nome"));
    produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));    
    produto.setUrlFoto(rs.getString("urlFoto"));
    produto.setValor(rs.getBigDecimal("valor"));    
    return produto;
    
  }

}

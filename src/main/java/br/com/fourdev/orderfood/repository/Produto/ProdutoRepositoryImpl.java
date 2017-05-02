package br.com.fourdev.orderfood.repository.Produto;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Produto;
 

@Repository("produtoRepository")
public class ProdutoRepositoryImpl implements ProdutoRepository {

  private static final String INSERT_PRODUTO = "insert into pcprodut (id, nome, descricao) values (?,?,?)";
  private static final String UPDATE_PRODUTO = "update pcprodut set descricao = ? where codprod = ?";
  private static final String DELETE_PRODUTO = "delete from pcprodut where codprod = ?";  
  private JdbcOperations template;
  private ProdutoRowMapper mapper;

  @Autowired
  public ProdutoRepositoryImpl(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
    mapper = new ProdutoRowMapper();
  }

  @Override
  public List<Produto> todos() {
    String sql = "select codprod, descricao, pvenda from pcprodut";
    return template.query(sql, mapper);
  }

  @Override
  public void salvar(Produto produto) {
    template.update(INSERT_PRODUTO, produto.getId(),produto.getNome(), produto.getDescricao() );
  }
  
  @Override
  public void alterar(Produto produto) {
     String sql = UPDATE_PRODUTO;
     template.update(sql, produto.getDescricao(), produto.getId());
  }

  @Override
  public void delete(Produto produto) {
    String sql = DELETE_PRODUTO;
    template.update(sql, produto.getId());
  }  

//  @Override
//  public void salvar(List<Produto> produtos) {
    // template.batchUpdate(INSERT_PRODUTO, produtos, 100, (ps, p) -> {
    // ps.setInt(1, p.getCodigo());
    // ps.setInt(2, p.getValor());
    // ps.setBoolean(3, p.isAtivo());
    // });
//  }

//  @Override
//  public Produto comCodigo(int codigo) {
//    try {
//      String sql = "select * from pcprodut where codprod = ?";
//      return template.queryForObject(sql, mapper, codigo);
//      
//    } catch (DataAccessException ex) {
//      String msg = String.format("Produt de código: %s inválido", codigo);
//      throw new IllegalArgumentException(msg);
//    }
//  }
//
//  @Override
//  public long totalDeProdutos() {
//    String sql = "select count(codigo) from pcprodut";
//    return template.queryForObject(sql, Long.class);
//  }
//
//  @Override
//  public List<Long> codigos() {
//    String sql = "select codprod from pcprodut";
//    return template.queryForList(sql, Long.class);
//  }
//
//  @Override
//  public List<Map<String, Object>> codigosValores() {
//    String sql = "select codprod, pvenda from pcprodut";
//    return template.queryForList(sql);
//  }
//
//  @Override
//  public long valorTotal() {
//    String sql = "select sum(pvenda) from pcprodut";
//    Long value = template.queryForObject(sql, Long.class);
//    return value != null ? value : 0;
//  }


 
}

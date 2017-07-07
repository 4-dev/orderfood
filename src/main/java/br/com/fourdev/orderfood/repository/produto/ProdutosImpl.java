package br.com.fourdev.orderfood.repository.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fourdev.orderfood.model.Produto;

@Repository("produtoRepository")
public class ProdutosImpl implements ProdutosQueries {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	
//	public ProdutosImpl() {
//		String query1 = "set search_path to orderfood, public";
//		jdbcTemplate.update(query1, new Object[]{});
//	}
	// private static final String INSERT_PRODUTO = "insert into pcprodut (id,
	// nome, descricao) values (?,?,?)";
	// private static final String UPDATE_PRODUTO = "update pcprodut set
	// descricao = ? where codprod = ?";
	// private static final String DELETE_PRODUTO = "delete from pcprodut where
	// codprod = ?";

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Produto> selectProdutoList() {

		try {
//			String query1 = "set search_path to orderfood, public";
//			jdbcTemplate.update(query1, new Object[]{});
			
			
			String query = "select * from ORDERFOOD.produto";
			return jdbcTemplate.query(query, new BeanPropertyRowMapper<Produto>(Produto.class));
		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public Produto selectProdutoPorId(String id) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "select * from produto where id=? ";
		return jdbcTemplate.queryForObject(query, new Object[] { id },
				new BeanPropertyRowMapper<Produto>(Produto.class));
	
	}

	public void insertProduto(Produto produto) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});

		String query = "insert into produto(id, nome, descricao, urlFoto, volume, valor, qtestoque, categoria) "
				+ " values (/, ?,?,?,?,?,?,?) ";
		jdbcTemplate.update(query,
				new Object[] { produto.getId(), produto.getNome(), produto.getDescricao(), produto.getFoto(),
						produto.getVolume(), produto.getValor(), produto.getQtestoque(),
						produto.getCategoria().getDescricao() });
	}

	public void updateProduto(String id, Produto produto) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});
		
		String query = "update produto set nome=?, descricao=? where id=? ";
		jdbcTemplate.update(query,
				new Object[] { produto.getId(), produto.getNome(), produto.getDescricao(), produto.getFoto(),
						produto.getVolume(), produto.getValor(), produto.getQtestoque(),
						produto.getCategoria().getDescricao() });
	}

	public void deleteProduto(String id) {
		String query = "delete from produto where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

}

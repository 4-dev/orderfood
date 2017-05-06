package br.com.fourdev.orderfood.repository.produto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Categoria;
import br.com.fourdev.orderfood.model.Produto;

@Repository("produtoRepository")
public class ProdutoRepositoryImpl implements ProdutoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// private static final String INSERT_PRODUTO = "insert into pcprodut (id,
	// nome, descricao) values (?,?,?)";
	// private static final String UPDATE_PRODUTO = "update pcprodut set
	// descricao = ? where codprod = ?";
	// private static final String DELETE_PRODUTO = "delete from pcprodut where
	// codprod = ?";

	public List<Produto> selectProdutoList() {
		String query = "select * from produto";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Produto.class));
	}

	public Produto selectProdutoPorId(String id) {
		String query = "select * from produto where id=? ";
		return (Produto) jdbcTemplate.queryForObject(query, new Object[] { id },
				new BeanPropertyRowMapper(Produto.class));
	}

	public void insertProduto(Produto produto) {

		String query = "insert into produto(id, nome, descricao, urlFoto, volume, valor, quantidadeEstoque, categoria) "
				+ " values (?,?,?,?,?,?,?,?) ";
		jdbcTemplate.update(query,
				new Object[] { produto.getId(), produto.getNome(), produto.getDescricao(), produto.getUrlFoto(),
						produto.getVolume(), produto.getValor(), produto.getQuantidadeEstoque(),
						produto.getCategoria().getId() });
	}

	public void updateProduto(String id, Produto produto) {
		String query = "update produto set nome=?, descricao=? where id=? ";
		jdbcTemplate.update(query,
				new Object[] { produto.getId(), produto.getNome(), produto.getDescricao(), produto.getUrlFoto(),
						produto.getVolume(), produto.getValor(), produto.getQuantidadeEstoque(),
						produto.getCategoria().getId() });
	}

	public void deleteProduto(String id) {
		String query = "delete from produto where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

}

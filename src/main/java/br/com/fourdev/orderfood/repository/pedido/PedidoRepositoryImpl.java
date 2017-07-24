package br.com.fourdev.orderfood.repository.pedido;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fourdev.orderfood.model.Cliente;
import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.model.StatusPedido;
import br.com.fourdev.orderfood.model.Venda;
import br.com.fourdev.orderfood.repository.cliente.Clientes;

@Repository("PedidoRepository")
public class PedidoRepositoryImpl implements PedidoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private Clientes clientes;
	
	public List<Pedido> selectPedidoList() {

		try {
			String query1 = "set search_path to orderfood, public";
			jdbcTemplate.update(query1, new Object[] {});

			String query = "select * from cabpedido cab " + "INNER JOIN  itempedido item ON (cab.numped = item.numped)";
			List<Pedido> listaPedidos = jdbcTemplate.query(query, new PedidoRowMapper());

			return listaPedidos;
		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Pedido> retornaStatusPedido(StatusPedido statusPedido) {

		try {
			String query1 = "set search_path to orderfood, public";
			jdbcTemplate.update(query1, new Object[] {});

			String query = "select * from cabpedido cab where status = ?";
			return jdbcTemplate.query(query, new Object[] { statusPedido.getDescricao() }, new PedidoRowMapper());

		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pedido selectPedidoPorId(String numped) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "select * from Pedido where id=? ";
		return (Pedido) jdbcTemplate.queryForObject(query, new Object[] { numped },
				new BeanPropertyRowMapper(Pedido.class));
	}

	public List<Pedido> retornaPedidoPorMesa(int idmesa, StatusPedido statusPedido) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
//		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		String query = "SELECT cab.* " + "	FROM mesa_pedido mp, cabpedido cab " + "	WHERE mp.numped = cab.numped "
				+ "	 AND mp.idmesa = ? " + "	 AND cab.status = ? " + " ORDER BY CAB.NUMPED";
		pedidos = jdbcTemplate.query(query, new Object[] { idmesa, statusPedido.getDescricao() }, new PedidoRowMapper());
		
//		for (Pedido pedido : listaPedidos) {
//			Cliente cliente = new Cliente();
//			cliente = clientes.findOne( pedido.getCliente().getIdcliente());
//			pedido.setCliente(cliente);
//			pedidos.add(pedido);
//		}
		return pedidos;
	}

	public List<ItemPedido> retornaItemPorPedido(int numPedido) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT prod.*, ip.numped, ip.quantidade FROM itempedido ip, produto prod WHERE ip.produto = prod.id AND ip.numped = ? ORDER BY prod.id";
		return jdbcTemplate.query(query, new Object[] { numPedido }, new ItemPedidoRowMapper());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertPedido(List<Pedido> pedidos) {
		try {

			try {
				String query1 = "set search_path to orderfood, public";
				jdbcTemplate.update(query1, new Object[] {});

				for (Pedido pedido : pedidos) {
					String qry = "select max(numped) + 1 as numped from itempedido";
					long numped = jdbcTemplate.queryForObject(qry, Long.class);

					for (ItemPedido itemPedido : pedido.getItens()) {
						System.out.println(itemPedido.getProduto());

						// Itens do Pedido
						String query = "insert into itempedido(numped, " + "produto, " + "quantidade, "
								+ "valorUnitario) " + " values (?, ?, ?, ?) ";
						jdbcTemplate.update(query, new Object[] { numped, itemPedido.getProduto(),
								itemPedido.getQuantidade(), itemPedido.getValorUnitario() });
					}
					
				

					// Cabeçalho do Pedido
					String query = "INSERT INTO cabpedido" + "( numped," + // 1
							"  datacriacao," + // 2
							"  valordesconto," + // 3
							"  valortotal," + // 4
							"  observacao," + // 5
							"  dataentrega," + // 6
							"  datacancel," + // 7
							"  status," + // 8
							"  idcliente," + // 9
							"  idmesa," + // 10
							"  idusuario" + // 11
							" ) VALUES (" + "  ?," + // 1
							"  CURRENT_DATE," + // 2
							"  ?," + // 3
							"  ?," + // 4
							"  ?," + // 5
							"  CURRENT_DATE," + // 6
							"  NULL," + // 7
							"  ?," + // 8
							"  ?," + // 9
							"  ?," + // 10
							"  ?" + // 11
							")";

					jdbcTemplate.update(query,
							new Object[] { numped, // 1
									pedido.getValorDesconto(), // 3
									pedido.getValorTotal(), // 4
									pedido.getObservacao(), // 5
									pedido.getStatus().getDescricao(), // 8
									pedido.getCliente().getIdcliente(), // 9
									pedido.getMesa().getIdmesa(), // 10
									1});//11
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());// TODO: handle exception

			}

		} catch (InvalidResultSetAccessException e) {
			throw new RuntimeException(e);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void atualizarStatusPedido(Pedido pedido) {
		try {
			String query = "update CABPEDIDO set STATUS = ?, idvenda = ? where NUMPED = ? ";
			jdbcTemplate.update(query, new Object[] { pedido.getStatus().getDescricao(), pedido.getVenda().getId(), pedido.getNumped() });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Pedido.getDescricao(), Pedido.getUrlFoto(),
		// Pedido.getVolume(), Pedido.getValor(), Pedido.getQuantidadeEstoque(),
		// Pedido.getCategoria().getDescricao() });
	}

	
	public void atualizarVenda(Venda venda, Pedido pedido) {
		try {
			String query = "update CABPEDIDO set idvenda = ? where NUMPED = ? ";
			jdbcTemplate.update(query, new Object[] { venda.getId(), pedido.getNumped() });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		PedidoRepositoryImpl d = new PedidoRepositoryImpl();
		Pedido p = new Pedido();
		p.setNumped(123);
		d.updatePedido(p);
	}

	public void updatePedido(Pedido pedido) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});
		try {
			Class c1 = pedido.getClass();
			// Map fieldMap = new HashMap();
			MapSqlParameterSource params = new MapSqlParameterSource();
			Field[] valueObjFields = c1.getDeclaredFields();
			String queryMontada = "";
			for (int i = 0; i < valueObjFields.length; i++) {
				valueObjFields[i].setAccessible(true);
				Object newObj;
				newObj = valueObjFields[i].get(pedido);
				String fieldName = valueObjFields[i].getName();
				if ((newObj != null) && (!fieldName.equalsIgnoreCase("numped"))) {
					queryMontada += fieldName + " = :" + fieldName;
					params.addValue(fieldName, newObj);
				}
			}
			String queryUpdate = "update cabpedido set ";
			queryUpdate += queryMontada;
			queryUpdate += " WHERE NUMPED = :NUMPED";
			params.addValue("numped", pedido.getNumped());
			namedParameterJdbcTemplate.update(queryUpdate, params);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void deletePedido(String id) {
		String query = "delete from Pedido where id=?";
		jdbcTemplate.update(query, new Object[] { id });
	}

	@Override
	public void insertPedido(Pedido pedido) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pedido> retornaPedidoPorCliente(long idcliente, StatusPedido statusPedido) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT cab.* " 
				+ "	FROM  cabpedido cab " 
				+ "	WHERE cab.idcliente = ?" 
				+ "	 AND cab.status = ? " 
				+ " ORDER BY CAB.NUMPED";
		return jdbcTemplate.query(query, new Object[] { idcliente, statusPedido.getDescricao() }, new PedidoRowMapper());
	}

	@Override
	public List<Pedido> retornaPedidoPorVenda(int idvenda) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[] {});

		String query = "SELECT cab.* " 
				+ "	FROM  cabpedido cab " 
				+ "	WHERE cab.idvenda = ?" 
				+ " ORDER BY CAB.NUMPED";
		return jdbcTemplate.query(query, new Object[] { idvenda }, new PedidoRowMapper());
	}

}

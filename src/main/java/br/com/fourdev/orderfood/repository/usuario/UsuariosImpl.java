package br.com.fourdev.orderfood.repository.usuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fourdev.orderfood.model.Usuario;

@Repository("usuariosImpl")
public class UsuariosImpl implements UsuariosQueries {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		String query1 = "set search_path to orderfood, public";
		jdbcTemplate.update(query1, new Object[]{});

		
		String query = "SELECT  * from usuario WHERE email LIKE lower(:email) AND ativo = true";

		return manager.createNativeQuery(query, Usuario.class).setParameter("email", email).getResultList().stream()
				.findFirst();
	}

	// jpql
	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario",
				String.class).setParameter("usuario", usuario).getResultList();
	}

}

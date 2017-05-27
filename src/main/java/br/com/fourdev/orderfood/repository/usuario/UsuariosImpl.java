package br.com.fourdev.orderfood.repository.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fourdev.orderfood.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@Autowired
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		String query = "SELECT  * from usuario WHERE email LIKE lower(:email) AND ativo = true";

		return manager
				.createNativeQuery(query, Usuario.class)
					.setParameter("email", email).getResultList()
						.stream().findFirst();
	}
}

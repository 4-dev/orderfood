package br.com.fourdev.orderfood.repository.usuario;

import java.util.List;
import java.util.Optional;

import br.com.fourdev.orderfood.model.Usuario;

public interface UsuariosQueries {
	
	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);

}

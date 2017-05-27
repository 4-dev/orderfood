package br.com.fourdev.orderfood.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Usuario;
import br.com.fourdev.orderfood.repository.usuario.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private Usuarios usuarios;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> usuarioOptional = usuarios.porEmailEAtivo(email);
		
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Email ou senha incorretos!"));
		
		return new User(usuario.getEmail(), usuario.getPassword(), new HashSet<>());
	}

}

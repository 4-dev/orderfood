package br.com.fourdev.orderfood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.fourdev.orderfood.model.Usuario;
import br.com.fourdev.orderfood.repository.usuario.Usuarios;
import br.com.fourdev.orderfood.service.exception.EmailCadastradoException;
import br.com.fourdev.orderfood.service.exception.SenhaObrigatoraException;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	public void salva(Usuario usuario) {
		
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent()) {
			throw new EmailCadastradoException("email do usuário já cadastrado!");
		}

		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getPassword())){
			
			throw new SenhaObrigatoraException("A senha é Obrigatória para um novo usuário!");
		
		}
		
		if(usuario.isNovo()){
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}
		
		usuarios.save(usuario);
	}
	
}

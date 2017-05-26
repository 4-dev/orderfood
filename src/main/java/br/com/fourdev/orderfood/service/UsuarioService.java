package br.com.fourdev.orderfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fourdev.orderfood.model.Usuario;
import br.com.fourdev.orderfood.repository.usuario.Usuarios;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	

	public void salva(Usuario usuario) {

			usuarios.save(usuario);
	}
	
}

package br.com.fourdev.orderfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fourdev.orderfood.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@GetMapping("/novo")
	public String novo(Usuario usuario){
		
		return "usuario/cadastro-usuario";
	}

}

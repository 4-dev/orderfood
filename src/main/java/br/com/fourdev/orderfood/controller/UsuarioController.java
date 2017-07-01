package br.com.fourdev.orderfood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.model.Usuario;
import br.com.fourdev.orderfood.repository.Grupos;
import br.com.fourdev.orderfood.repository.usuario.Usuarios;
import br.com.fourdev.orderfood.service.UsuarioService;
import br.com.fourdev.orderfood.service.exception.EmailCadastradoException;
import br.com.fourdev.orderfood.service.exception.SenhaObrigatoraException;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Grupos grupos;

	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario){
		ModelAndView modelAndView = new ModelAndView("usuario/cadastro-usuario");
		modelAndView.addObject(usuario);
		modelAndView.addObject("grupos", grupos.findAll());
		
		return modelAndView;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			
			return novo(usuario);
		}
		
		try {
			
			usuarioService.salva(usuario);
			
		} catch (EmailCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
			
		}catch (SenhaObrigatoraException e) {
			result.rejectValue("password", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		
		attributes.addFlashAttribute("mensagem", "Usuário Salvo Com Sucesso!");
		
		return new ModelAndView("redirect:novo");
	}
	
	@GetMapping
	public ModelAndView pesquisa(){
		ModelAndView modelAndView = new ModelAndView("usuario/pesquisa-usuario");
		
		modelAndView.addObject("usuarios", usuarios.findAll());
		modelAndView.addObject("grupos", grupos.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id")Usuario usuario){
		ModelAndView modelAndView = novo(usuario);
		modelAndView.addObject(usuario);
		modelAndView.addObject("grupos", grupos.findAll());

		return modelAndView;
	}
	
	

}

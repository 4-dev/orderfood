package br.com.fourdev.orderfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fourdev.orderfood.model.Produto;

@Controller
public class ProdutoController {
	
	@GetMapping("/novo")
	public String novo(){
		return "produto/cadastro-produto";
	}
	
	
	@PostMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView modelAndView = new ModelAndView();
		
		
		
		return modelAndView;
		
	}

}

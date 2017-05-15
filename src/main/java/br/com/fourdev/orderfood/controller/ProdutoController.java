package br.com.fourdev.orderfood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fourdev.orderfood.model.Categoria;
import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.model.Unidade;
import br.com.fourdev.orderfood.repository.produto.Produtos;
import br.com.fourdev.orderfood.service.ProdutoService;

@Controller
public class ProdutoController {
	
	//@Autowired
	//private ProdutoService produtoService;
	
	@Autowired
	private Produtos produtos;
	
	@GetMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView modelAndView = new ModelAndView("produto/cadastro-produto");
		modelAndView.addObject(produto);
		modelAndView.addObject("categorias", Categoria.values());
		modelAndView.addObject("unidades", Unidade.values());
		
		return modelAndView ;
	}
	
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Produto produto, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return novo(produto);
		}
		
		//produtoService.insertProduto(produto);
		
		produtos.save(produto);
		
		attributes.addAttribute("mensagem", "Produto Salvo com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}

}

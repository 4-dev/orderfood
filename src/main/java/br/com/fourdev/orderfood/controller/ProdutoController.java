package br.com.fourdev.orderfood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fourdev.orderfood.model.Categoria;
import br.com.fourdev.orderfood.model.Produto;
import br.com.fourdev.orderfood.model.Unidade;
import br.com.fourdev.orderfood.repository.produto.Produtos;
import br.com.fourdev.orderfood.service.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
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
	
	
	@RequestMapping(value={"/novo", "{\\d+}"}, method=RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return novo(produto);
		}
		
		produtoService.salvar(produto);
		
		
		attributes.addFlashAttribute("mensagem", "Produto Salvo com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisa(){
		ModelAndView modelAndView = new ModelAndView("produto/pesquisa-produto");
		
		modelAndView.addObject("produtos", produtos.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id")Produto produto){
		ModelAndView modelAndView = novo(produto);
		modelAndView.addObject(produto);
		return modelAndView;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Produto produto){
	
		produtos.delete(produto);
		
		return ResponseEntity.ok().build();
	}

}


	

















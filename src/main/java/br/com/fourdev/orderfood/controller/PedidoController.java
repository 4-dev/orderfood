package br.com.fourdev.orderfood.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.service.PedidoService;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/abertos")
	public ModelAndView status(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/lista-pedido-abertos");
		modelAndView.addObject(pedido);
		
		return modelAndView ;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/cadastro-pedido");
		modelAndView.addObject(pedido);
		
		return modelAndView ;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return novo(pedido);
		}
//		pedidoService.insertMesa(pedido);
		attributes.addFlashAttribute("mensagem", "Mesa Salva com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}

}

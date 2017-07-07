package br.com.fourdev.orderfood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fourdev.orderfood.model.ItemPedido;
import br.com.fourdev.orderfood.model.Pedido;
import br.com.fourdev.orderfood.service.MesaService;
import br.com.fourdev.orderfood.service.PedidoService;
//
@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private MesaService mesaService;
	
	private Pedido pedido;
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	
	public boolean listaEmAndamento;
	
	
	@GetMapping("/abertos")
	public ModelAndView listaPedidosAbertos(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/lista-pedido-abertos");
			modelAndView.addObject(pedido);
			modelAndView.addObject(pedidoService);
		return modelAndView ;
	}
	
	
	@GetMapping("/emandamento")
	public ModelAndView listaPedidosEmAndamento(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/lista-pedido-emandamento");
			modelAndView.addObject(pedido);
			modelAndView.addObject(pedidoService);
		return modelAndView ;
	}
	 
	@GetMapping("/fechados")
	public ModelAndView listaPedidosFechados(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/lista-pedido-fechados");
			modelAndView.addObject(pedido);
			modelAndView.addObject(pedidoService);
		return modelAndView ;
	}
	@GetMapping("/cancelados")
	public ModelAndView listaPedidosCancelados(Pedido pedido){
		ModelAndView modelAndView = new ModelAndView("pedido/lista-pedido-cancelados");
			modelAndView.addObject(pedido);
			modelAndView.addObject(pedidoService);
		return modelAndView ;
	}	


	@PostMapping("/additem")
	public ModelAndView additem(@Valid ItemPedido itemPedido, BindingResult result, RedirectAttributes attributes){
		itens.add(itemPedido);
//		pedidoService.insertMesa(pedido);
		attributes.addFlashAttribute("mensagem", "Mesa Salva com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}
	

	
	@GetMapping("/novo")
	public ModelAndView novo(Pedido pedido, ItemPedido itemPedido){
		ModelAndView modelAndView = new ModelAndView("pedido/cadastro-pedido");
		modelAndView.addObject(pedido);
		modelAndView.addObject(itens);
		return modelAndView ;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return novo(pedido, null);
		}
//		pedidoService.insertMesa(pedido);
		attributes.addFlashAttribute("mensagem", "Mesa Salva com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}

}

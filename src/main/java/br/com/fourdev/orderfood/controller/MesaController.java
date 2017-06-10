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
import br.com.fourdev.orderfood.service.MesaService;
import br.com.fourdev.orderfood.service.PedidoService;

@Controller
@RequestMapping("mesa")
public class MesaController {
	
	@Autowired
	private MesaService mesaService;
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/status")
	public ModelAndView status(Mesa mesa){
		ModelAndView modelAndView = new ModelAndView("mesa/status-mesa");
		modelAndView.addObject(mesa);
		modelAndView.addObject(mesaService);
		modelAndView.addObject(pedidoService);
		return modelAndView ;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Mesa mesa){
		ModelAndView modelAndView = new ModelAndView("mesa/cadastro-mesa");
		modelAndView.addObject(mesa);
		modelAndView.addObject("mesas", mesaService.selectMesaList());
		modelAndView.addObject("qtdMesas", mesaService.contaMesas() + 1);
		return modelAndView ;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid Mesa mesa, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return novo(mesa);
		}
		
		mesaService.insertMesa(mesa);
		
		
		attributes.addFlashAttribute("mensagem", "Mesa Salva com Sucesso!");
		
		return new ModelAndView("redirect:novo");
		
	}

	
	@PostMapping("/status/finalizarmesa")
	public ModelAndView finalizarMesa(@Valid int idmesa, BindingResult result, RedirectAttributes attributes){
		mesaService.finalizarMesa(idmesa);
		attributes.addFlashAttribute("mensagem", "Mesa Salva com Sucesso!");
		return new ModelAndView("redirect:status");
	}
	
}

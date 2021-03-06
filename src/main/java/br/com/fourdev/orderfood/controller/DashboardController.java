package br.com.fourdev.orderfood.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.fourdev.orderfood.dto.VendaMesDTO;
import br.com.fourdev.orderfood.dto.VendaMesaDTO;
import br.com.fourdev.orderfood.repository.venda.Vendas;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
	
	@Autowired
	private Vendas vendas;
	
	@GetMapping("/inicio")
	public ModelAndView showInicio(){
		ModelAndView modelAndView = new ModelAndView("dashboard/painel-inicio");
	
		modelAndView.addObject("totalNoAno", vendas.valorTotalNoAno());
		modelAndView.addObject("totalNoMes", vendas.valorTotalNoMes());
		modelAndView.addObject("agora", LocalDateTime.now());
		modelAndView.addObject("totalPedidos", vendas.totalDePedidos());
		modelAndView.addObject("totalItens", vendas.totalDeItens());
		modelAndView.addObject("totalCincoMeses", vendas.valorCincoMeses());
		
		return modelAndView ;
	}
	
	@GetMapping("/pordia")
	public @ResponseBody BigDecimal totalPorDia(){
		return vendas.vendaDoDia();
	}
	
	@GetMapping("/pormes")
	public @ResponseBody List<VendaMesDTO> totalPorMes(){
		return vendas.totalPorMes();
	}
	
	@GetMapping("/pormesa")
	public @ResponseBody List<VendaMesaDTO> totalPorMesa(){
		return vendas.totalPorMesa();
	}
	
	@GetMapping("/sobre")
	public String sobre(){
		return "dashboard/sobre";
	}

}

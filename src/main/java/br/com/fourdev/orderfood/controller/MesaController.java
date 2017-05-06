
package br.com.fourdev.orderfood.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fourdev.orderfood.model.Mesa;
import br.com.fourdev.orderfood.service.MesaService;

@RestController
@RequestMapping("/mesa")
public class MesaController {

	final static Logger logger = LoggerFactory.getLogger(MesaController.class);
	@Autowired
	private MesaService mesaService;

	@RequestMapping(value = "/listarMesas", method = RequestMethod.GET)
	public List<Mesa> selectMesaList() {
		return mesaService.selectMesaList();
	}

	@RequestMapping(value = "/buscarMesaPorCodigo/{id}", method = RequestMethod.GET)
	public Mesa selectMesaById(@PathVariable("id") String id) {
		return mesaService.selectMesaById(id);
	}

	@RequestMapping(value = "/savarMesa", method = RequestMethod.POST)
	public void insertMesa(@RequestBody Mesa mesa) {

		logger.debug("id=" + mesa.getId());
		logger.debug("Name=" + mesa.getDescricao());
		logger.debug("age=" + mesa.getDescricao());

		mesaService.insertMesa(mesa);
	}

	@RequestMapping(value = "/atualizarMesa/{id}", method = RequestMethod.PUT)
	public void updateMesa(@RequestBody Mesa mesa, @PathVariable("id") String id) {
		logger.debug("id=" + id);
		mesaService.updateMesa(id, mesa);
	}

	@RequestMapping(value = "/deletarMesa/{id}", method = RequestMethod.DELETE)
	public void deleteMesa(@PathVariable("id") String id) {
		mesaService.deleteMesa(id);
	}

}
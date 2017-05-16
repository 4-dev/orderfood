package br.com.fourdev.orderfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fourdev.orderfood.dto.Foto;
import br.com.fourdev.orderfood.service.ProdutoService;
import br.com.fourdev.orderfood.storage.FotoReader;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private ProdutoService produtoService;

	@Autowired(required = false)
	private FotoReader fotoReader;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Foto upload(@PathVariable Long id
			, @RequestParam("files[]") MultipartFile[] files) {
		
		String url = produtoService.salvarFoto(id, files[0]);
		return new Foto(url);
	}
	
	@RequestMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome) {
		return fotoReader.recuperar(nome);
	}
	
}

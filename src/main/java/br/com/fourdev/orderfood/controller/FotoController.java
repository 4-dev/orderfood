package br.com.fourdev.orderfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;

import br.com.fourdev.orderfood.dto.FotoDTO;
import br.com.fourdev.orderfood.service.ProdutoService;
import br.com.fourdev.orderfood.storage.FotoReader;
import br.com.fourdev.orderfood.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/foto")
public class FotoController {
	
	@PostMapping
	public DeferredResult<String> upload(@RequestParam("files[]") MultipartFile[] files){
		
		DeferredResult<String> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado));
		thread.start();
		
		
		
		return resultado;
	}
	
//	@Autowired
//	private ProdutoService produtoService;
//
//	@Autowired(required = false)
//	private FotoReader fotoReader;
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
//	public Foto upload(@PathVariable Long id
//			, @RequestParam("files[]") MultipartFile[] files) {
//		
//		String url = produtoService.salvarFoto(id, files[0]);
//		return new Foto(url);
//	}
//	
//	@RequestMapping("/{nome:.*}")
//	public byte[] recuperar(@PathVariable String nome) {
//		return fotoReader.recuperar(nome);
//	}
//	
}

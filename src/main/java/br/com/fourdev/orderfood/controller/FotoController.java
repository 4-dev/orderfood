package br.com.fourdev.orderfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.fourdev.orderfood.dto.FotoDTO;
import br.com.fourdev.orderfood.storage.FotoStorage;
import br.com.fourdev.orderfood.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/foto")
public class FotoController {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files){
		
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
		thread.start();
		
		
		return resultado;
	}
	
	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		
		byte[] recuperaFotoTemporaria = fotoStorage.recuperaFotoTemporaria(nome);
		
		return recuperaFotoTemporaria;
	}
	
	@DeleteMapping("/temp/{nome:.*}")
	public void apagarFotoTemporaria(@PathVariable("nome") String nomeFoto){
		fotoStorage.apagarFotoTemporaria(nomeFoto);
	}
	
}

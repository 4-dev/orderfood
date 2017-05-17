package br.com.fourdev.orderfood.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {
	

	private MultipartFile[] files;
	private DeferredResult<String> resultado;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<String> resultado) {
		this.files = files;
		this.resultado = resultado;

	}

	@Override
	public void run() {
		System.out.println(">>>>>>file: "+ files[0].getSize());
		
	
		resultado.setResult("OK! Foto Recebida com Sucesso!");
	}

}

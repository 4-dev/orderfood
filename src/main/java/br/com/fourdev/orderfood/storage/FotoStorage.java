package br.com.fourdev.orderfood.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public String salvarTemporiariamente(MultipartFile[] files);

	public byte[] recuperaFotoTemporaria(String nome);

	public void apagarFotoTemporaria(String nomeFoto);
	
}

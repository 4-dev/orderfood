package br.com.fourdev.orderfood.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	public void salvarTemporiariamente(MultipartFile[] files);
	
	
}

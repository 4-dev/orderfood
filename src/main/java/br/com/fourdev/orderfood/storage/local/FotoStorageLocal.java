package br.com.fourdev.orderfood.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.fourdev.orderfood.storage.FotoStorage;

@Profile("storage-local")
@Component
public class FotoStorageLocal implements FotoStorage{

	private Path local;
	
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".orderfoodfotos"));
		
	}
	
	public FotoStorageLocal(Path path){
		this.local = path;
		criarPastas();
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			System.out.println(">>>>>>>>>>Pastar criadas para salvar fotos.");
			System.out.println(">>>>>>>>>>Pasta Default: "+ this.local.toAbsolutePath());
			System.out.println(">>>>>>>>>>Pasta Default: "+ this.localTemporario.toAbsolutePath());
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
		
	}

	@Override
	public void salvarTemporiariamente(MultipartFile[] files) {
		
		System.out.println(">>>>>>>> Salvando foto temporariamente!");
		
	}

	


//	@Override
//	public byte[] recuperar(String nome) {
//		try {
//			return Files.readAllBytes(this.local.resolve(nome));
//		} catch (IOException e) {
//			throw new RuntimeException("Erro recuperando a foto", e);
//		}
//	}

}

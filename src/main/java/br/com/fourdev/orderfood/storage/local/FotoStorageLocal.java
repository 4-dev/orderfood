package br.com.fourdev.orderfood.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

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
		this(getDefault().getPath("imagens", "orderfoodimagens"));
		
		
	}
	
	public FotoStorageLocal(Path path){
		this.local = path;
		criarPastas();
	}
	

	@Override
	public String salvarTemporiariamente(MultipartFile[] files) {
		
		String novoNome = null;
		
		if (files != null && files.length > 0) {
			
			MultipartFile arquivo = files[0];
			
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString()+getDefault().getSeparator()
						+ novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao Salvar imagem na pasta temporaria!", e);
			}
			
		}
		
		return novoNome;
	}
	
	@Override
	public byte[] recuperaFotoTemporaria(String nome) {
		
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar imagem!", e);
		}
	}
	
	@Override
	public void apagarFotoTemporaria(String nomeFoto) {
		try {
			Files.deleteIfExists(this.localTemporario.resolve(nomeFoto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao excluir foto!", e);
		}
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
	
	private String renomearArquivo(String nomeOriginal){
		
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		return novoNome;
		
	}

	
	

}

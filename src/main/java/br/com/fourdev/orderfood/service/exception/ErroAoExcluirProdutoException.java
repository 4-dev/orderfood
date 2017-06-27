package br.com.fourdev.orderfood.service.exception;

public class ErroAoExcluirProdutoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErroAoExcluirProdutoException(String message) {
		super(message);
	}
}

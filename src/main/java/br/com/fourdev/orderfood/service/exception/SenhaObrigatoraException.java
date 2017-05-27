package br.com.fourdev.orderfood.service.exception;

public class SenhaObrigatoraException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SenhaObrigatoraException(String message) {
		super(message);
	}

}

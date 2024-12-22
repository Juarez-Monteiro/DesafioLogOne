package com.teste.pratico.business.exception;

public class EntidadeExisteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EntidadeExisteException(String mensagem) {
	        super(mensagem);
	    }
}

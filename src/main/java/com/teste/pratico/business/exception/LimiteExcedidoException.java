package com.teste.pratico.business.exception;

public class LimiteExcedidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LimiteExcedidoException(String mensagem) {
		super(mensagem);
	}

}

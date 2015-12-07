package br.unipe.mlp.banco.modelo.exception;


@SuppressWarnings("serial")
public class ContaNaoEncontradaException extends Exception {
	
	public ContaNaoEncontradaException (String mensagem){
		super(mensagem);
	}
	

}

package br.unipe.mlp.banco.conta.dados;

import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.modelo.exception.ContaJaCadastradaException;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public interface IRepositorioContas {
	void inserir(Conta conta) throws ContaJaCadastradaException;
	Conta procurar(String numero) throws ContaNaoEncontradaException;
	Conta procurarNome(String numero) throws ContaNaoEncontradaException;
	void remover(String numero) throws ContaNaoEncontradaException;
	void  atualizar(Conta conta)  throws ContaNaoEncontradaException;
	boolean existe(String numero);
	String listaContas();
}

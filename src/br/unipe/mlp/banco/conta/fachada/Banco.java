package br.unipe.mlp.banco.conta.fachada;

import java.io.IOException;
import java.util.ArrayList;

import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ServicosConta;
import br.unipe.mlp.banco.modelo.exception.ContaJaCadastradaException;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class Banco {
	
	private static ServicosConta servicos;
	
	public Banco(){
		try {
			servicos = new ServicosConta();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cadastraConta(Conta conta) throws ContaJaCadastradaException{
		  servicos.cadastra(conta);
	}
	public void remover(String numero) throws ContaNaoEncontradaException{
		servicos.remover(numero);
	}
	public void alterar(Conta conta) throws ContaNaoEncontradaException{
		servicos.atualizar(conta);
	}
	public Conta buscarNome(String nome) throws ContaNaoEncontradaException{
		return	servicos.procurarNome(nome);
	}
	public Conta buscarNumero(String numero) throws ContaNaoEncontradaException{
		return servicos.procurar(numero);
	}
	public String listar(){
		return servicos.ListaContas();
	}
	public static ArrayList<Conta> listTable(){
		return (ArrayList<Conta>) ServicosConta.getListConta();
	}
	
}

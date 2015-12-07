package br.unipe.mlp.banco.conta.modelo;

import java.io.IOException;
import java.util.List;

import br.unipe.mlp.banco.conta.dados.IRepositorioContas;
import br.unipe.mlp.banco.conta.dados.RepositoryContasHashSet;
import br.unipe.mlp.banco.modelo.exception.ContaJaCadastradaException;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class ServicosConta {

	private static IRepositorioContas contas;
	
	public ServicosConta() throws IOException {
		ServicosConta.contas = new RepositoryContasHashSet();
	}
	
	public void cadastra(Conta conta)throws ContaJaCadastradaException{				
		 contas.inserir(conta);		
	}
	
	public boolean atualizar(Conta conta) throws ContaNaoEncontradaException{
		if(contas.existe(conta.getNumero())){
			contas.atualizar(conta);
			return true;
		}
		return false;
	}
	public Conta procurar(String numero) throws ContaNaoEncontradaException{
		return contas.procurar(numero);
	}
	public Conta procurarNome(String nome) throws ContaNaoEncontradaException{
		return ((RepositoryContasHashSet) contas).procurarNome(nome);
	}
	public boolean remover(String numero) throws ContaNaoEncontradaException{
		Conta conta = contas.procurar(numero);
		if(conta != null){
			contas.remover(numero);
			return true;
		}				
		return false;
	}
	public boolean existe(String numero){
		return contas.existe(numero);
	}
	public String ListaContas(){
		return contas.listaContas();
	}
	

	public static List<Conta> getListConta() {		
		return (List<Conta>) RepositoryContasHashSet.getListContas();
	}
}

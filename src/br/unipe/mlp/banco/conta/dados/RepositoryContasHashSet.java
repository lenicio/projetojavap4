package br.unipe.mlp.banco.conta.dados;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import br.unipe.mlp.banco.modelo.exception.ContaJaCadastradaException;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class RepositoryContasHashSet implements IRepositorioContas {
	
	private static Set<Conta> contas;
	private SqlServer sqlBanco;
	private PropriedadesSqlServer configSql;
	private static String tabela = "Contas";

	
	public RepositoryContasHashSet() throws IOException {
	RepositoryContasHashSet.contas = new HashSet<Conta>();
	configSql = new PropriedadesSqlServer();
	sqlBanco = new SqlServer(configSql.getUrl(), configSql.getDatabase(),
				   configSql.getUsuario(), configSql.getSenha(),configSql.getPorta());
	getDadosBancoDB();
	}
	
	
	public void inserir(Conta conta) throws ContaJaCadastradaException{
		
		if(!existe(conta.getNumero())){
			sqlBanco.conectar();
			contas.add(conta);
			inserirContaSql(tabela, conta.getNome(), conta.getNumero(), conta.getSaldo());
			sqlBanco.desconectar();			
		} else {
			throw new ContaJaCadastradaException("Conta já Cadastrada");	
			
		}
		
	}
	public Conta  procurar(String numero)throws ContaNaoEncontradaException{
		java.util.Iterator<Conta> i = contas.iterator();
		while(i.hasNext()){
			Conta conta = i.next();
			if(conta.getNumero().equals(numero)){
				return conta;
			}
		}
		throw new ContaNaoEncontradaException("Conta não Encontrada");
		
	}
	public void  remover(String numero) throws ContaNaoEncontradaException{
		Conta conta = procurar(numero);
		if(conta!=null){			
			sqlBanco.conectar();
			contas.remove(conta);  
			removerContaSql(tabela, numero);
			sqlBanco.desconectar();
		} 
		throw new ContaNaoEncontradaException("Conta não Encontrada");
		
	}
	public void  atualizar(Conta conta)  throws ContaNaoEncontradaException{

		if(existe(conta.getNumero())){
			java.util.Iterator<Conta> i = contas.iterator();
			while(i.hasNext()){
				Conta contaAux = i.next();
				if(contaAux.getNumero().equals(conta.getNumero())){
					contaAux.setNome(conta.getNome());
					contaAux.setNumero(conta.getNumero());
					contaAux.setSaldo(conta.getSaldo());
					sqlBanco.conectar();				
					updateContaSql(tabela, conta.getNome(), conta.getNumero(), conta.getSaldo());
					sqlBanco.desconectar();
				}
			}
		}else {
			throw new ContaNaoEncontradaException("Conta não Encontrada");
			
		}			
	}
	public boolean existe(String numero){
		java.util.Iterator<Conta> i = contas.iterator();
		while(i.hasNext()){
			Conta conta = i.next();
			if(conta.getNumero().equals(numero)){
				return true;
			}
		}
		return false;
	}
	public Conta procurarNome(String nome) throws ContaNaoEncontradaException{
		java.util.Iterator<Conta> i = contas.iterator();
		while(i.hasNext()){
			Conta conta = i.next();
			if(conta.getNome().equals(nome)){
				return conta;
			}
		}
		throw new ContaNaoEncontradaException("Conta não Encontrada");
		
	}
	
	public static List<Conta> getListContas(){
		ArrayList<Conta> contasTabela = new ArrayList<Conta>();
		for(Conta conta : contas){
			contasTabela.add(conta);
		}
			return contasTabela;
	}
	public void getDadosBancoDB(){
		sqlBanco.conectar();
		ResultSet dados = sqlBanco.executar("SELECT * FROM Contas");
		
		
		try {
			while(dados.next()){
				ContaCorrente conta = new ContaCorrente();
				conta.setNome(dados.getString(2)); 
				conta.setNumero(dados.getString(3));
				conta.setSaldo(dados.getDouble(4));
				contas.add(conta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlBanco.desconectar();
	}
	
	private int inserirContaSql(String tabela,String nome,String numero,double saldo){
		return sqlBanco.inserir("INSERT INTO Contas (Nome,Numero,Saldo) VALUES('"+nome+"','"+numero+"','"+saldo+"')");
	}
	private int removerContaSql(String tabela,String numero){
		return sqlBanco.inserir("DELETE FROM Contas WHERE Numero='"+numero+"'");
	}
	private int updateContaSql(String tabela,String nome,String numero,double saldo){
		return sqlBanco.inserir("UPDATE "+tabela+" SET Nome = '"+nome+"', Saldo = '"+saldo+"' WHERE Numero = '"+numero+"'");
	}

	public String listaContas() {
		// TODO Auto-generated method stub
		return null;
	}


	public void mudarLingua(boolean lingua) {
		// TODO Auto-generated method stub
		
	}
}

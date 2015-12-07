package br.unipe.mlp.banco.conta.modelo;

import java.util.Comparator;

public class ComparatorContaNumeroDecrescente implements Comparator<Conta> {

	public int compare(Conta conta1, Conta conta2) {
		if(conta1.getNumero().compareTo(conta2.getNumero())==-1){
			return -1;
		} else if(conta1.getNumero().compareTo(conta2.getNumero())==1){
			return 1;
		}
			
		return 0;
	}

}

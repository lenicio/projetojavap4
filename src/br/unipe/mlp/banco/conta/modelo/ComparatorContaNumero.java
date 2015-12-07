package br.unipe.mlp.banco.conta.modelo;

import java.util.Comparator;


public class ComparatorContaNumero implements Comparator<Conta> {

	public int compare(Conta conta1, Conta conta2) {
		
		return conta1.getNumero().compareTo(conta2.getNumero());
	}

}

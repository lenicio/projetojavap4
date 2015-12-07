package br.unipe.mlp.banco.conta.modelo;

import java.util.Comparator;

public class ComparatorContaDecrescenteSaldo implements Comparator<Conta> {

	public int compare(Conta o1, Conta o2) {
		int num = (int) o1.getSaldo();
		
		if(num > (int)o2.getSaldo()){
			return -1;
		} else if(num < (int)o2.getSaldo()){
			return 1;
		}
		return 0; 
	}

}

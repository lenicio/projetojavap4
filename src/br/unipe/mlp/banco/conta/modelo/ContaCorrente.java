package br.unipe.mlp.banco.conta.modelo;


public class ContaCorrente extends Conta {

	public ContaCorrente(String nome,String numero,double saldo) {
		super(nome,numero,saldo);		
	}

	public ContaCorrente() {
		// TODO Auto-generated constructor stub
	}

	public boolean creditarTaxa(double taxa){
		if(this.saldo>0){
			this.saldo += this.saldo * taxa * 2; 
			return true;
		}
		return false;
	}
	
	public boolean creditar(double valor){
		if(valor>0){
			this.saldo+=valor;
			this.saldo-= 0.10;
			return true;
		}
		return false;
	}
	
	public String toString(){			
		return String.format("Nome: %s\nNumero: %s\nSaldo: %.2f\n",this.nome,this.numero,this.saldo);
	}

	public int compareTo(Conta conta) {
		return nome.compareTo(conta.getNome());
	}
	
}

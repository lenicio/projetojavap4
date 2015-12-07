package br.unipe.mlp.banco.conta.modelo;


public abstract class Conta implements Comparable<Conta>{

	protected String numero;
	protected String nome;
	protected double saldo;
	
	public Conta(String nome,String numero,double saldo){
		this.nome=nome;
		this.numero=numero;
		this.saldo=saldo;
	}
	public Conta(){
		
	}
	
	public abstract boolean creditarTaxa(double taxa);	
	
	public int compareTo(String nome){
		return nome.compareTo(nome);
	}
	public boolean equals(String nome){
		return nome.equals(nome);
	}
	
	public String getNumero() {
		return numero;
	}	
	public double getSaldo() {
		return saldo;
	}		
	
	public boolean creditar(double saldo){	
		if (saldo<0)
			return false;
		
		this.saldo += saldo;
		return true;		
	}	
	public boolean debitar(double valor){		
		if (valor<0 && valor > saldo){
			System.out.println("Impossivel debitar");
			return false;
		}
		
		this.saldo -=valor;
		return true;		
	}	
	public String toString(){
		return "";
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
}
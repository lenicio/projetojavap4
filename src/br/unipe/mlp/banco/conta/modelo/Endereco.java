package br.unipe.mlp.banco.conta.modelo;

public class Endereco {
	private String rua;
	private String telefone;
	
	public Endereco(String rua,String telefone){
		this.rua = rua;
		this.telefone = telefone;
	}
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString(){
		return String.format("Endereço: %s, Fone: %s",rua,telefone);
	}
}

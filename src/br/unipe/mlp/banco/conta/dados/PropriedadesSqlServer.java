package br.unipe.mlp.banco.conta.dados;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropriedadesSqlServer{
	
	private Properties configBD;
	private InputStream input;
	private String caminho = "database/database.properties";
	private String url;
	private String database;
	private String usuario;
	private String senha;
	private String porta;
	
	public PropriedadesSqlServer() throws IOException{
		configBD = new Properties();
		input = getClass().getClassLoader().getResourceAsStream(caminho);
		configBD.load(input);	
		carregarValores();
	}
		

	
	public void carregarValores(){
		this.url = configBD.getProperty("conexao.url");
		this.database = configBD.getProperty("conexao.database");
		this.usuario = configBD.getProperty("conexao.usuario");
		this.senha = configBD.getProperty("conexao.senha");
		this.porta = configBD.getProperty("conexao.porta");
	}
	
	public String toString(){
		return String.format(" url: %s\n database: %s\n usuario: %s\n senha: %s\n porta: %s",url,database,usuario,senha,porta);
	}

	public Properties getConfigBD() {
		return configBD;
	}

	public void setConfigBD(Properties configBD) {
		this.configBD = configBD;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}
	
	
}

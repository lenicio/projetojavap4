package br.unipe.mlp.banco.conta.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GerarRelatorio{

   private JFileChooser fc = null;
   private File diretorio = null;
   private PrintWriter pw = null;
   private ArrayList<Conta> contas = null;
 
   
    public GerarRelatorio(JFrame parent,ArrayList<Conta> contas) {
    	   this.contas=contas; 
    	 
    	   fc = new JFileChooser();
		  
		   fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		   int res = 0;
		   res = fc.showDialog(null,"Salvar");
		   
		   if(res == JFileChooser.APPROVE_OPTION){
		       diretorio = fc.getSelectedFile();
		       JOptionPane.showMessageDialog(parent, "Relatório Salvo em: " + diretorio.getPath() + "\\relatorio.txt");	        		       
		       gravarArquivo();
		   }
		   else
			   JOptionPane.showMessageDialog(null, "Voce não selecionou nenhum diretorio.");          
       }
  
	 private void gravarArquivo(){
		 
		 try {
				pw = new PrintWriter(new FileWriter(diretorio.getPath().concat("\\relatorio.txt")));
				@SuppressWarnings("unused")
				String list[] ;
				pw.println("----------------------------------------");
				pw.println("Relatório de Contas Cadastradas");
				
				pw.println();
				for(Conta conta : contas){
					pw.println("Nome: "+conta.getNome());
					pw.println("Numero: "+conta.getNumero());
					pw.println("Saldo: "+conta.getSaldo());		
					pw.println();
				}
				pw.println("----------------------------------------");
				pw.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}  
	 }
}

package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class TelaRemover {

	private JFormattedTextField txtnumero = null;
	private SpringLayout sl = null;
	private JPanel t_Cad = null;
	private JDialog telaRemDialog;
	private static Banco banco ;
	private JLabel remover = null;
	private JButton botao = null;
	
	public TelaRemover(JFrame pai,Banco banco,boolean lingua){
		String titulo;
		if(!lingua){
			titulo="REMOVER CONTA";
		} else {
			titulo="REMOVE ACCOUNT";
		}
		telaRemDialog = new JDialog(pai,titulo,true);	
		t_Cad = new JPanel();
		sl = new SpringLayout();
		TelaRemover.banco=banco;
		remover = new JLabel();
		botao = new JButton();
		
		t_Cad.add(botao);
		
		
		try {
			txtnumero = new JFormattedTextField(new MaskFormatter("####-##"));
			txtnumero.setValue("0000-00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		montarTelaRemover();
		mostrarTela();
	}

	private void mostrarTela(){		
		telaDeRemover();
		botaoConcluido();
		montarTela();
	}
	
	private void montarTelaRemover(){
		remover.setText("REMOVER Nº DE CONTA");
		botao.setText("CONFIRMAR");
	}
	
		private void telaDeRemover() {			
			sl.putConstraint(SpringLayout.NORTH, remover, 80, SpringLayout.NORTH, t_Cad);
			sl.putConstraint(SpringLayout.WEST, remover, 58, SpringLayout.WEST, t_Cad);
			sl.putConstraint(SpringLayout.NORTH, txtnumero,28, SpringLayout.NORTH, remover);
			sl.putConstraint(SpringLayout.WEST, txtnumero, -95, SpringLayout.EAST, remover);
			t_Cad.add(remover);
			t_Cad.add(txtnumero);
	}
		
		private void botaoConcluido() {			
			
			sl.putConstraint(SpringLayout.SOUTH, botao, -20, SpringLayout.SOUTH, t_Cad);
			sl.putConstraint(SpringLayout.WEST, botao, 72, SpringLayout.WEST, t_Cad);
			t_Cad.add(botao);
		
			botao.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				
				if(!txtnumero.getText().equals("")){			
					
				try {		
					banco.remover(txtnumero.getText());
					telaRemDialog.dispose();
				} catch (ContaNaoEncontradaException e1){
					JOptionPane.showMessageDialog(null, "Conta Removida");
									
					
				}
					
						
				}else {
					JOptionPane.showMessageDialog(null, "Não foi inserido nenhum número");
					
				}
			}
			
		});
		}

		private void montarTela() {
			
			telaRemDialog.setSize(250, 250);
			telaRemDialog.setLayout(new FlowLayout());
			t_Cad.setLayout(sl);
			telaRemDialog.setContentPane(t_Cad);
			t_Cad.setSize(250, 250);
			telaRemDialog.setResizable(false);
			telaRemDialog.setLocationRelativeTo(null);
			telaRemDialog.setVisible(true);	
		}
	
}

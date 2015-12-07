package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class TelaSobre {
	private JDialog telaSobre = null;
	private JButton btnNewButton = null;
	private SpringLayout springLayout = null;
	private JLabel lblDesenvolvido = null;
	
	public TelaSobre(JFrame principal) {
		telaSobre = new JDialog(principal,"Sobre o MI-D10",true);
		btnNewButton = new JButton();
		springLayout = new SpringLayout();
		telaSobre.setLayout(springLayout);
		lblDesenvolvido = new JLabel();
		lblDesenvolvido.setText("Desenvolvido por Lenicio de Souza");
		btnNewButton.setText("Fechar");
		montarEstrutura();
		
	}
	private void montarEstrutura(){
		btnNewButton = new JButton("Fechar");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 170, SpringLayout.WEST, telaSobre.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -20, SpringLayout.SOUTH, telaSobre.getContentPane());
		telaSobre.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				telaSobre.dispose();				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblDesenvolvido, 10, SpringLayout.NORTH, telaSobre.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDesenvolvido, 1, SpringLayout.WEST, telaSobre.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblDesenvolvido, 96, SpringLayout.NORTH, telaSobre.getContentPane());
		lblDesenvolvido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telaSobre.setSize(400, 200);
		telaSobre.getContentPane().add(lblDesenvolvido);
		telaSobre.setResizable(false);
		telaSobre.setLocationRelativeTo(null);
		telaSobre.setVisible(true);	
	}
}

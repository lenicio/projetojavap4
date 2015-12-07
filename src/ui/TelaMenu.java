package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.ComparatorContaCrescenteSaldo;
import br.unipe.mlp.banco.conta.modelo.ComparatorContaDecrescenteSaldo;
import br.unipe.mlp.banco.conta.modelo.ComparatorContaNumero;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaTabelaModelo;
import br.unipe.mlp.banco.conta.modelo.GerarRelatorio;
import br.unipe.mlp.banco.modelo.exception.ContaJaCadastradaException;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class TelaMenu{

     private static JFrame	tela;
	 private static JMenuBar menuBar = null;
	 private static JMenu 	sistemaMenu = null;
	 private static JMenu	ajudaMenu = null;
	 private static JMenu  relatorioMenu = null;
	 private static JMenuItem relatorioSubMenu = null; 
	 private static JMenuItem cadastroSubMenu = null;
	 private static JMenuItem alteraSubMenu = null;
	 private static JMenuItem removeSubMenu = null;
	 private static JMenuItem sobreMenu = null;
	 private static JTable tabela;
	 private static ArrayList<Conta> contasBusca = null;
	 private static ArrayList<Conta> contasLista = null;
	 private static ArrayList<Conta> contasRelatorio = null;
	 private static TableModel tableModel = null;
	 private static ContaTabelaModelo contaTable = null;
	 private static Banco banco;
	 private static JScrollPane barraRolagem;
	 private static SpringLayout springLayout = null;
	 @SuppressWarnings("rawtypes")
	 private static JComboBox comboBox = null;
	 @SuppressWarnings("rawtypes")
	private static JComboBox comboTradutor = null;
	 private static JPanel painelTabela = null;
	 private String selectCombo = "";
	 private static JFormattedTextField textBusca = null;
	 private static JCheckBox checkNome = null;
	 private static JCheckBox checkNumero = null;
	 private static JCheckBox checkSaldo = null;
	 private static JCheckBox checkCresc = null;
	 private static JCheckBox checkDescresc = null;
	 private static JButton btnComfirmar = null;
	
	@SuppressWarnings("unused")
	private static TelaCadastro telaCadastro=null;
	@SuppressWarnings("unused")
	private static TelaRemover telaRemover=null;
	@SuppressWarnings("unused")
	private static TelaAlterar telaAlterar=null;
	private static String col1="Nome",col2="Numero",col3="Saldo";

	 
	 public void iniciar() {
			
			TelaMenu tela = new TelaMenu();
			springLayout = new SpringLayout();
			tela.mostrarTela();
		}
	 
	public TelaMenu(){
		tela = new JFrame();
		banco = new Banco();
	}

	
	private void mostrarTela(){
		tela.setTitle("MI-D10");	
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(800, 600);
		ajustaFundo("resources/imagens/TelaPrincipal.jpg");
		tela.setResizable(false);
		tela.setLocationRelativeTo(null);
		tela.setLayout(null);
		tela.getContentPane().setLayout(springLayout);
		montarMenu();
		tela.setVisible(true);
	}
	 public void ajustaFundo(String arquivo) {  
		JLabel fundo = new JLabel(new ImageIcon(arquivo));  
		fundo.setLayout(new FlowLayout());  
		tela.setContentPane(fundo);  
	}

	@SuppressWarnings({ "rawtypes" })
	private void montarMenu(){
		menuBar = new JMenuBar();
		comboBox = new JComboBox();
		ajudaMenu = new JMenu();
		sistemaMenu = new JMenu();
		relatorioMenu = new JMenu();
		cadastroSubMenu = new JMenuItem();
		alteraSubMenu = new JMenuItem();
		removeSubMenu = new JMenuItem();
		relatorioSubMenu = new JMenuItem();
		sobreMenu = new JMenuItem();
		checkNome = new JCheckBox();
		checkNumero = new JCheckBox();
		checkSaldo = new JCheckBox();
		checkCresc = new JCheckBox();
		checkDescresc = new JCheckBox();
		btnComfirmar = new JButton();
		painelTabela = new JPanel();
		contasBusca = new ArrayList<Conta>();
		contasLista = new ArrayList<Conta>();
		contasRelatorio = new ArrayList<Conta>();
		contaTable =  new ContaTabelaModelo(contasLista,col1,col2,col3);
		tabela = new JTable(contaTable);
		textBusca = new JFormattedTextField();
	
		
		selectCombo = "Listar";		
		barraRolagem = new JScrollPane(tabela);
		
		barraRolagem.setViewportView(tabela);
		painelTabela.add(barraRolagem);
		tabela.setDefaultRenderer(Object.class, contaTable.CellRendererPane());
		tela.add(painelTabela);
		
		tela();
		menuBar.add(sistemaMenu);
		menuBar.add(relatorioMenu);
		menuBar.add(ajudaMenu);
		tela.setJMenuBar(menuBar);
		sistemaMenu.add(cadastroSubMenu);
		sistemaMenu.add(alteraSubMenu);
		sistemaMenu.add(removeSubMenu);
		relatorioMenu.add(relatorioSubMenu);
		ajudaMenu.add(sobreMenu);
		montarEstrutura();		
	}
	
	@SuppressWarnings("unchecked")
	private void tela(){
		ajudaMenu.setText("Ajuda");
		sistemaMenu.setText("CRUD");
		relatorioMenu.setText("Relatório");
		cadastroSubMenu.setText("Cadastrar");
		alteraSubMenu.setText("Alterar");
		removeSubMenu.setText("Remover");
		relatorioSubMenu.setText("Salvar Como");
		sobreMenu.setText("Desenvolvedor");
		int select = comboBox.getSelectedIndex();
		if(select==-1){
			comboBox.addItem("Listar");
			comboBox.addItem("Buscar");
			comboBox.setSelectedItem("Listar");
		} else {
			comboBox.removeAllItems();
			comboBox.addItem("Listar");
			comboBox.addItem("Buscar");
			comboBox.setSelectedIndex(select);
		}
		checkCresc.setText("Crescente");
		checkDescresc.setText("Decrescente");
		checkNome.setText("Nome");
		checkNumero.setText("Numero Conta");
		checkSaldo.setText("Saldo");
		btnComfirmar.setText("Confirmar");
		
	
		col1 = "Nome";
		col2 = "Numero";
		col3 = "Saldo";
		
		if(!btnComfirmar.isEnabled()){
			if(comboBox.getSelectedIndex()==0){
				mudarTabela(new ArrayList<Conta>());
			} else {
				mudarTabela(new ArrayList<Conta>());
			}
		} else {
			if(comboBox.getSelectedIndex()==0){
				mudarTabela(contasLista);
			} else if(comboBox.getSelectedIndex()==1){
				mudarTabela(contasBusca);
			}
		}
	}
	
	private void mudarTabela(ArrayList<Conta> contas){
		painelTabela.removeAll();
		contaTable =  new ContaTabelaModelo(contas,col1,col2,col3);
		tabela = new JTable(contaTable);		
		barraRolagem.setViewportView(tabela);
		painelTabela.add(barraRolagem);
		tabela.setDefaultRenderer(Object.class, contaTable.CellRendererPane());
	}	
	
	@SuppressWarnings({"rawtypes" })
	private void montarEstrutura(){	
		
		comboTradutor = new JComboBox();
		comboTradutor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				tela.revalidate();
			}
		});	
		
		relatorioSubMenu.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				contasRelatorio= banco.listTable();
				Collections.sort(contasRelatorio);
				@SuppressWarnings({ "unused" })
				GerarRelatorio relatorio = new GerarRelatorio(tela,contasRelatorio);
				
			}
		});
		
		montarOpListar();
		btnComfirmar.setEnabled(false);
		textBusca.setEnabled(false);
		
		cadastroSubMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					telaCadastro = new TelaCadastro(tela,banco,false);
				} catch (ContaJaCadastradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					if(selectCombo.equals("List")||selectCombo.equals("Listar")&&btnComfirmar.isEnabled()){
						contasLista = Banco.listTable();
						tableModel = new ContaTabelaModelo(contasLista,col1,col2,col3);
						tabela.setModel(tableModel);
						tela.revalidate();
					}				
			}
		});

		
		removeSubMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 telaRemover = new TelaRemover(tela,banco,false);				 
						if(selectCombo.equals("List")||selectCombo.equals("Listar")&&btnComfirmar.isEnabled()){
							contasLista = Banco.listTable();
							tableModel = new ContaTabelaModelo(contasLista,col1,col2,col3);
							tabela.setModel(tableModel);
							tela.revalidate();
						}					
			}
		});
		alteraSubMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaAlterar = new TelaAlterar(tela,banco,false);
				
					if(selectCombo.equals("List")||selectCombo.equals("Listar")&&btnComfirmar.isEnabled()){
						contasLista = Banco.listTable();
						tableModel = new ContaTabelaModelo(contasLista,col1,col2,col3);
						tabela.setModel(tableModel);
						tela.revalidate();
					}			
				
			}
		});
		
		sobreMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				TelaSobre telaSobre = new TelaSobre(tela);
				
			}
		});
		
		ActionListener eventCombo = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(selectCombo.equals("List")||selectCombo.equals("Listar")){
					desSelectCheck();
					desabiliCheck();
					tableModel = new ContaTabelaModelo(new ArrayList<Conta>(),col1,col2,col3);
					tabela.setModel(tableModel);
					checkNome.setEnabled(true);
					checkNumero.setEnabled(true);
					checkSaldo.setEnabled(true);
					checkCresc.setEnabled(true);
					checkDescresc.setEnabled(true);
					
				} else if (selectCombo.equals("Buscar")||selectCombo.equals("Search")){
					desSelectCheck();
					tableModel = new ContaTabelaModelo(new ArrayList<Conta>(),col1,col2,col3);
					tabela.setModel(tableModel);
					checkNome.setEnabled(true);
					checkNumero.setEnabled(true);
					checkSaldo.setEnabled(false);
					checkCresc.setEnabled(false);
					checkDescresc.setEnabled(false);
					btnComfirmar.setEnabled(true);					
					textBusca.setEnabled(true);					
					
				}
				textBusca.setText("");
			}
			
		};
		
		comboBox.addActionListener(eventCombo);
		comboBox.addItemListener( new ItemListener() {
			
			public void itemStateChanged(ItemEvent item) {
				
				if(item.getStateChange() == ItemEvent.SELECTED){		
					selectCombo = (String)comboBox.getSelectedItem();				
				
				}				
			}
		});
		ActionListener evenBoxChk = new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				if(selectCombo.equals("Buscar")||selectCombo.equals("Search")){
					if(checkNome.isSelected()){
						checkNumero.setEnabled(false);
					}else if(checkNumero.isSelected()){	
						checkNome.setEnabled(false);						
					} else if(!checkNome.isSelected()&&!checkNumero.isSelected()) {
						checkNome.setEnabled(true);
						checkNumero.setEnabled(true);
					}
				
				} else if(selectCombo.equals("List")||selectCombo.equals("Listar")) {
					contasLista=banco.listTable();
					if(checkCresc.isSelected()&&checkNome.isSelected()) {
						Collections.sort(contasLista);						
						btnComfirmar.setEnabled(true);
					} else if(checkDescresc.isSelected()&&checkNome.isSelected()) {
						Collections.reverse(contasLista);						
						btnComfirmar.setEnabled(true);
					} else if(checkCresc.isSelected()&&checkSaldo.isSelected()) {
						Collections.sort(contasLista,new ComparatorContaCrescenteSaldo());
						btnComfirmar.setEnabled(true);
					} else if(checkDescresc.isSelected()&&checkSaldo.isSelected()) {
						Collections.sort(contasLista,new ComparatorContaDecrescenteSaldo());
						btnComfirmar.setEnabled(true);
					} else if(checkCresc.isSelected()&&checkNumero.isSelected()){
						Collections.sort(contasLista,new ComparatorContaNumero());
						btnComfirmar.setEnabled(true);
					}  else if(checkDescresc.isSelected()&&checkNumero.isSelected()){
						Collections.sort(contasLista,new ComparatorContaNumero());
						Collections.reverse(contasLista);
						btnComfirmar.setEnabled(true);
					}	else if(!checkCresc.isSelected()||!checkSaldo.isSelected()||!checkDescresc.isSelected()||!checkNome.isSelected()||!checkNumero.isSelected()) {
						checkCresc.setEnabled(true);
						checkDescresc.setEnabled(true);
						checkNome.setEnabled(true);
						checkSaldo.setEnabled(true);
						checkNumero.setEnabled(true);
						btnComfirmar.setEnabled(false);
					}
					if(checkNome.isSelected()){
						checkSaldo.setEnabled(false);
						checkNumero.setEnabled(false);
					}else if(checkSaldo.isSelected()){
						checkNome.setEnabled(false);
						checkNumero.setEnabled(false);
					} else if (checkNumero.isSelected()){
						checkSaldo.setEnabled(false);
						checkNome.setEnabled(false);
					}
					if(checkCresc.isSelected()){
						checkDescresc.setEnabled(false);
					}else if(checkDescresc.isSelected()){
						checkCresc.setEnabled(false);
					}
	
				}				
				
			}
		};
		
		btnComfirmar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(selectCombo.equals("List")||selectCombo.equals("Listar")){
					
					tableModel = new ContaTabelaModelo(contasLista,col1,col2,col3);
					tabela.setModel(tableModel);
					
				} 
				if((selectCombo.equals("Buscar")||selectCombo.equals("Search"))&&checkNome.isSelected()){
					Conta conta1 =null;
					
					try {
						conta1 =  banco.buscarNome((String)textBusca.getText());
						
						contasBusca.add(conta1);
						
						
						if(contasBusca.isEmpty()){
							contasBusca.clear();
						}						
						tableModel = new ContaTabelaModelo(contasBusca,col1,col2,col3);
						tabela.setModel(tableModel);
					} catch (ContaNaoEncontradaException e1) {
						
						// TODO Auto-generated catch block
						
						JOptionPane.showMessageDialog(tela, e1.getMessage());
						tableModel = new ContaTabelaModelo(new ArrayList<Conta>(),col1,col2,col3);
					}
					
				} if((selectCombo.equals("Buscar")||selectCombo.equals("Search"))&&checkNumero.isSelected()){
					Conta conta2=null;
				
					try {
						
						conta2 = banco.buscarNumero((String)textBusca.getText());
						
						contasBusca.add(conta2);							
						
						if(contasBusca.isEmpty()){
							contasBusca.clear();
						}						
						tableModel = new ContaTabelaModelo(contasBusca,col1,col2,col3);
						tabela.setModel(tableModel);
					} catch (ContaNaoEncontradaException e1) {
						
						
						JOptionPane.showMessageDialog(tela, e1.getMessage());
						tableModel = new ContaTabelaModelo(new ArrayList<Conta>(),col1,col2,col3);
					}
					
				}
					
					tela.revalidate();
			
			}
		});
	
		checkCresc.addActionListener(evenBoxChk);
		checkDescresc.addActionListener(evenBoxChk);
		checkNome.addActionListener(evenBoxChk);
		checkNumero.addActionListener(evenBoxChk);
		checkSaldo.addActionListener(evenBoxChk);
	}
	private void desabiliCheck(){
		checkNome.setEnabled(false);
		checkNumero.setEnabled(false);
		checkSaldo.setEnabled(false);
		btnComfirmar.setEnabled(false);
		checkCresc.setEnabled(false);
		checkDescresc.setEnabled(false);
		textBusca.setEnabled(false);	
	}
	private void desSelectCheck(){
		checkNome.setSelected(false);
		checkNumero.setSelected(false);
		checkSaldo.setSelected(false);
		btnComfirmar.setSelected(false);
		checkCresc.setSelected(false);
		checkDescresc.setSelected(false);
	}
	private void montarOpListar(){		
		
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 140, SpringLayout.NORTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 24, SpringLayout.WEST, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 140, SpringLayout.WEST, tela.getContentPane());
		tela.getContentPane().add(comboBox);
		
		springLayout.putConstraint(SpringLayout.WEST, checkNome, -4, SpringLayout.WEST, painelTabela);
		springLayout.putConstraint(SpringLayout.SOUTH, checkNome, -32, SpringLayout.NORTH, painelTabela);
		tela.getContentPane().add(checkNome);		
		
		springLayout.putConstraint(SpringLayout.WEST, checkNumero, 21, SpringLayout.EAST, checkNome);
		springLayout.putConstraint(SpringLayout.SOUTH, checkNumero, 0, SpringLayout.SOUTH, checkNome);
		tela.getContentPane().add(checkNumero);		
		
		springLayout.putConstraint(SpringLayout.NORTH, checkSaldo, 0, SpringLayout.NORTH, checkNome);
		springLayout.putConstraint(SpringLayout.WEST, checkSaldo, 16, SpringLayout.EAST, checkNumero);
		tela.getContentPane().add(checkSaldo);
		
		springLayout.putConstraint(SpringLayout.NORTH, textBusca, 2, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textBusca, 0, SpringLayout.WEST, painelTabela);
		springLayout.putConstraint(SpringLayout.EAST, textBusca, 68, SpringLayout.EAST, checkNumero);
		tela.getContentPane().add(textBusca);
		textBusca.setColumns(10);	
		
		springLayout.putConstraint(SpringLayout.NORTH, checkCresc, -28,SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, checkCresc, 295, SpringLayout.EAST, comboBox);
		tela.getContentPane().add(checkCresc);	
	
		springLayout.putConstraint(SpringLayout.NORTH, checkDescresc, 0, SpringLayout.NORTH, checkCresc);
		springLayout.putConstraint(SpringLayout.WEST, checkDescresc, 390, SpringLayout.EAST, comboBox);
		tela.getContentPane().add(checkDescresc); 
		
		springLayout.putConstraint(SpringLayout.NORTH, btnComfirmar, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnComfirmar, 0, SpringLayout.EAST, painelTabela);
		tela.getContentPane().add(btnComfirmar);	
	
		
		springLayout.putConstraint(SpringLayout.NORTH, painelTabela, -380, SpringLayout.SOUTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, painelTabela, 170, SpringLayout.WEST, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, painelTabela,-10, SpringLayout.SOUTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, painelTabela, -170, SpringLayout.EAST, tela.getContentPane());
			
	}		
	
}

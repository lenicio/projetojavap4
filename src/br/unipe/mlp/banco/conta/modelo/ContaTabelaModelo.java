package br.unipe.mlp.banco.conta.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;



public class ContaTabelaModelo extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
    private static final int COL_NOME = 0;
    private static final int COL_NUMERO = 1;
    private static final int COL_SALDO = 2;
    private static String col1="";
    private static String col2="";
    private static String col3="";
    private static List<Conta> valores;
    
    @SuppressWarnings("static-access")
	public ContaTabelaModelo(List<Conta> valores,String col1,String col2,String col3) {
		this.valores = valores;
		this.col1=col1;
		this.col2=col2;
		this.col3=col3;
	}
    @SuppressWarnings("static-access")
	public ContaTabelaModelo(List<Conta> valores){
    	this.valores=valores;
    }
    
    public int getColumnCount() {		
		return 3;
	}
    
	public String getColumnName(int column) { 	        
	   
	    if (column == COL_NOME) return col1;
	    if (column == COL_NUMERO) return col2;
	    if (column == COL_SALDO) return col3;	   
	    
	    return "";   
	}
	
	public Object getValueAt(int row, int column) {  
        
		Conta mod = valores.get(row);  
        if (column == COL_NOME) return mod.getNome();
        else if (column == COL_NUMERO) return mod.getNumero();
        else if (column == COL_SALDO) return mod.getSaldo();
        return "";  
    }
	public TableCellRenderer CellRendererPane(){
		DefaultTableCellRenderer def = new DefaultTableCellRenderer();
		def.setHorizontalAlignment(0);
		return def;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case COL_NOME:
			valores.get(rowIndex).setNome((String) aValue);
			break;
		case COL_NUMERO:
			valores.get(rowIndex).setNumero((String) aValue);
			break;
		case COL_SALDO:
			valores.get(rowIndex).setSaldo((Double) aValue);
			break;
		}	
		
	} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
        return String.class;  
    }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
        return false;  
    }
	 
	 public Conta get(int row) {  
        return valores.get(row);  
    }

	public int getRowCount() {
	
		return valores.size();
	}
    
	public void removeRow(int row){
		valores.remove(row);
	}

	public static void setCol1(String col1) {
		ContaTabelaModelo.col1 = col1;
	}

	public static void setCol2(String col2) {
		ContaTabelaModelo.col2 = col2;
	}

	public static void setCol3(String col3) {
		ContaTabelaModelo.col3 = col3;
	}
  

}

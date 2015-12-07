package Junit;

import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import junit.framework.TestCase;

public class TestCreditar extends TestCase {
 
  public void testMetodo() {
	  String nome = null;
		String numero = null;
		double saldo = 100;
		ContaCorrente c = new ContaCorrente(nome, numero, saldo);
		c.creditar(100);
		assertEquals(199,9, c.getSaldo());
  }
}
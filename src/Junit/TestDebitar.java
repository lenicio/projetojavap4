package Junit;

import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import junit.framework.TestCase;

public class TestDebitar extends TestCase {
 
  public void testMetodo() {
	  String nome = null;
		String numero = null;
		double saldo = 100;
		ContaCorrente c = new ContaCorrente(nome, numero, saldo);
		c.debitar(50);
		assertEquals(50,0, c.getSaldo());
  }
}
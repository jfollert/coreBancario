package coreBancario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	private final static int montoMinimoCLP = 2000;
	private final static int montoMinimoUSD = 10;
	private final static int montoMaximoRetiroCLP = 200000;
	private final static int montoMaximoRetiroUSD = 100;
	
	private Usuario usuarioPruebas;

	@BeforeEach
	void BeforeEach() throws Exception {
		int saldoCLP = 1000000;
		int saldoUSD = 500;
		usuarioPruebas = new Usuario(saldoCLP, saldoUSD, 
				montoMaximoRetiroCLP, montoMaximoRetiroUSD, 
				montoMinimoCLP, montoMinimoUSD);
	}

	@Test
	void testHistorialToString() {
		// Given
		// When
		// Then
		fail("Not yet implemented");
	}

	@Test
	void testResetCantidadOperaciones() {
		// Given
		// When
		usuarioPruebas.setCantidadOpsSesion(4);
		usuarioPruebas.resetCantOps();
		int cantOpsReseteado = usuarioPruebas.getCantidadOpsSesion();
		// Then
		assertEquals(cantOpsReseteado, 0, "No se ha podido resetear el contador de operaciones");
	}

	@Test
	void testDepositarMontoValidoCLP() {
		// Given
		String divisa = "CLP";
		int saldo = usuarioPruebas.getCuentaCLP().getSaldo();
		int montoDepositar = 5000;
		int saldoEsperado = saldo + montoDepositar;
		// When
		boolean resultado = usuarioPruebas.depositar(divisa, montoDepositar);
		int saldoActualizado = usuarioPruebas.getCuentaCLP().getSaldo();
		// Then
		assertTrue(resultado, "No se ha podido realizar el depósito de un monto válido");
		assertEquals(saldoEsperado, saldoActualizado, "El saldo luego del depósito no es el esperado");
	}
	
	@Test
	void testDepositarMontoValidoUSD() {
		// Given
		String divisa = "USD";
		int saldo = usuarioPruebas.getCuentaUSD().getSaldo();
		int montoDepositar = 20;
		int saldoEsperado = saldo + montoDepositar;
		// When
		boolean resultado = usuarioPruebas.depositar(divisa, montoDepositar);
		int saldoActualizado = usuarioPruebas.getCuentaUSD().getSaldo();
		// Then
		assertTrue(resultado, "No se ha podido realizar el depósito de un monto válido");
		assertEquals(saldoEsperado, saldoActualizado, "El saldo luego del depósito no es el esperado");
	}
	
	@Test
	void testRetirarMontoValidoCLP() {
		// Given
		String divisa = "CLP";
		int saldo = usuarioPruebas.getCuentaCLP().getSaldo();
		int montoRetirar = 5000;
		int saldoEsperado = saldo - montoRetirar;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		int saldoActualizado = usuarioPruebas.getCuentaCLP().getSaldo();
		// Then
		assertTrue(resultado, "No se ha podido realizar el retiro de un monto válido");
		assertEquals(saldoEsperado, saldoActualizado, "El saldo luego del retiro no es el esperado");
	}
	
	@Test
	void testRetirarMontoValidoUSD() {
		// Given
		String divisa = "USD";
		int saldo = usuarioPruebas.getCuentaUSD().getSaldo();
		int montoRetirar = 20;
		int saldoEsperado = saldo - montoRetirar;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		int saldoActualizado = usuarioPruebas.getCuentaUSD().getSaldo();
		// Then
		assertTrue(resultado, "No se ha podido realizar el retiro de un monto válido");
		assertEquals(saldoEsperado, saldoActualizado, "El saldo luego del retiro no es el esperado");
	}
	
	
	@Test
	void testRetiroConIntegerUSD() {
		// Given
		String divisa = "USD";
		int saldo = usuarioPruebas.getCuentaUSD().getSaldo();
		Integer montoRetirar = 20;
		int saldoEsperado = saldo - montoRetirar;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		int saldoActualizado = usuarioPruebas.getCuentaUSD().getSaldo();
		// Then
		assertTrue(resultado, "No se ha podido realizar el retiro de un monto válido");
		assertEquals(saldoEsperado, saldoActualizado, "El saldo luego del retiro no es el esperado");
	}
	
}

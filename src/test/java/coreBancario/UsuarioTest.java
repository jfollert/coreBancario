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
	void testHistorialVacioToString() {
		// Given
		String saldoCLP = String.valueOf(usuarioPruebas.getCuentaCLP().getSaldo());
		String saldoUSD = String.valueOf(usuarioPruebas.getCuentaUSD().getSaldo());
		String registroEsperado = "Cuentas creadas"; 
		// When
		String historial = usuarioPruebas.historialToString();
		boolean contieneSaldoCLP = historial.contains(saldoCLP);
		boolean contieneSaldoUSD = historial.contains(saldoUSD);
		boolean contieneRegistroEsperado = historial.contains(registroEsperado);
		// Then
		assertTrue(contieneSaldoCLP, "No se encuentra el saldo en CLP dentro del historial");
		assertTrue(contieneSaldoUSD, "No se encuentra el saldo en USD dentro del historial");
		assertTrue(contieneRegistroEsperado, "No se encuentra el registro de que se ha creado la cuenta en el historial");
	}
	
	@Test
	void testHistorialOperacionesCLP() {
		// Given
		int saldoInicial = usuarioPruebas.getCuentaCLP().getSaldo();
		int montoARetirar = 5000;
		int montoADepositar = 6000;
		int saldoEsperado = saldoInicial - montoARetirar + montoADepositar;
		
		// When
		usuarioPruebas.retirar("CLP", 5000);
		usuarioPruebas.depositar("CLP", 6000);
		String historial = usuarioPruebas.historialToString();
		
		// Then
		boolean contieneSaldoInicial = historial.contains(String.valueOf(saldoInicial));
		boolean contieneMontoARetirar = historial.contains(String.valueOf(montoARetirar));
		boolean contieneMontoADepositar = historial.contains(String.valueOf(montoADepositar));
		boolean contieneSaldoEsperado = historial.contains(String.valueOf(saldoEsperado));
		
		assertTrue(contieneSaldoInicial, "No se encuentra el saldo inicial en el historial");
		assertTrue(contieneMontoARetirar, "No se encuentra el monto retirado en el historial");
		assertTrue(contieneMontoADepositar, "No se encuentra el monto depositado en el historial");
		assertTrue(contieneSaldoEsperado, "No se encuentra el saldo final esperado en el historial");
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
	void testDepositarMontoInvalidoCLP() {
		// Given
		String divisa = "CLP";
		int montoDepositar = 10;
		// When
		boolean resultado = usuarioPruebas.depositar(divisa, montoDepositar);
		// Then
		assertFalse(resultado, "Se ha realizado un depósito en CLP inválido");
	}
	
	@Test
	void testDepositarMontoInvalidoUSD() {
		// Given
		String divisa = "USD";
		int montoDepositar = 1;
		// When
		boolean resultado = usuarioPruebas.depositar(divisa, montoDepositar);
		// Then
		assertFalse(resultado, "Se ha realizado un depósito en USD inválido");
		
	}
	
	@Test
	void testRetirarMontoInvalidoCLP() {
		// Given
		String divisa = "CLP";
		int montoRetirar = 500000;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		// Then
		assertFalse(resultado, "Se ha realizado un retiro en CLP inválido");
	}
	
	@Test
	void testRetirarMontoInvalidoUSD() {
		// Given
		String divisa = "USD";
		int montoRetirar = 500;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		// Then
		assertFalse(resultado, "Se ha realizado un retiro en USD inválido");
	}
	
	@Test
	void testRetirarDivisaInvalida() {
		// Given
		String divisa = "EUR";
		int montoRetirar = 20;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		// Then
		assertFalse(resultado, "Se ha operado con una divisa invalida");
	}
	
	@Test
	void testDepositarDivisaInvalida() {
		// Given
		String divisa = "EUR";
		int montoRetirar = 20;
		// When
		boolean resultado = usuarioPruebas.retirar(divisa, montoRetirar);
		// Then
		assertFalse(resultado, "Se ha operado con una divisa invalida");
	}
	
	
	
	
}

package coreBancario;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {
	private final static int montoMinimoCLP = 2000;
	private final static int montoMinimoUSD = 10;
	private final static int montoMaximoRetiroCLP = 200000;
	private final static int montoMaximoRetiroUSD = 100;
	
	private Cuenta cuentaCLP;
	private Cuenta cuentaUSD;
	
	@BeforeEach
	public void BeforeEach() throws Exception {
		cuentaCLP = new Cuenta("CLP", 1000000, montoMaximoRetiroCLP, montoMinimoCLP);
		cuentaUSD = new Cuenta("USD", 200, montoMaximoRetiroUSD, montoMinimoUSD);
	}

	@Test
	public void testDepositoMinimoPermitidoCLP() {
		// Given
		int saldo = cuentaCLP.getSaldo();
		int saldoEsperado = saldo + montoMinimoCLP;
				
		// When
		boolean resultado = cuentaCLP.depositar(montoMinimoCLP);
		int saldoActualizado = cuentaCLP.getSaldo();
		
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testDepositoMinimoPermitidoUSD() {
		// Given
		int saldo = cuentaUSD.getSaldo();
		int saldoEsperado = saldo + montoMinimoUSD;
		// When
		boolean resultado = cuentaUSD.depositar(montoMinimoUSD);
		int saldoActualizado = cuentaUSD.getSaldo();
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testRetiroMinimoPermitidoCLP() {
		// Given
		int saldo = cuentaCLP.getSaldo();
		int saldoEsperado = saldo - montoMinimoCLP;
		// When
		boolean resultado = cuentaCLP.retirar(montoMinimoCLP);
		int saldoActualizado = cuentaCLP.getSaldo();
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testRetiroMinimoPermitidoUSD() {
		// Given
		int saldo = cuentaUSD.getSaldo();
		int saldoEsperado = saldo - montoMinimoUSD;
		// When
		boolean resultado = cuentaUSD.retirar(montoMinimoUSD);
		int saldoActualizado = cuentaUSD.getSaldo();
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testRetiroMaximoPermitidoCLP() {
		// Given
		int saldo = cuentaCLP.getSaldo();
		int saldoEsperado = saldo - montoMaximoRetiroCLP;
		// When
		boolean resultado = cuentaCLP.retirar(montoMaximoRetiroCLP);
		int saldoActualizado = cuentaCLP.getSaldo();
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testRetiroMaximoPermitidoUSD() {
		// Given
		int saldo = cuentaUSD.getSaldo();
		int saldoEsperado = saldo - montoMaximoRetiroUSD;
		// When
		boolean resultado = cuentaUSD.retirar(montoMaximoRetiroUSD);
		int saldoActualizado = cuentaUSD.getSaldo();
		// Then
		assertTrue(resultado, "La operación de depósito ha fallado");
		assertEquals(saldoActualizado, saldoEsperado, "No se ha obtenido el saldo esperado");
	}
	
	@Test
	public void testComparacionDeCuentas() {
		// Given
		Cuenta nuevaCuentaCLP = new Cuenta("CLP", 1000000, montoMaximoRetiroCLP, montoMinimoCLP);
		Cuenta nuevaCuentaUSD = new Cuenta("USD", 200, montoMaximoRetiroUSD, montoMinimoUSD);
		// When
		// Then
		assertEquals(cuentaCLP, nuevaCuentaCLP, "La comparación entre cuentas iguales presenta fallas");
		assertEquals(cuentaUSD, nuevaCuentaUSD, "La comparación entre cuentas iguales presenta fallas");
		assertNotEquals(cuentaCLP, cuentaUSD, "La comparación entre cuentas diferentes presenta fallas");
		assertNotEquals(nuevaCuentaCLP, nuevaCuentaUSD, "La comparación entre cuentas diferentes presenta fallas");
	}

}

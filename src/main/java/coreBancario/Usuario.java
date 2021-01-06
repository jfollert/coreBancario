package coreBancario;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private Cuenta cuentaCLP;
	private Cuenta cuentaUSD;
	private int cantidadOpsSesion;
	final ArrayList<String> historial;
	
	public Usuario() {
		int saldoCLP = 1000000;
		int saldoUSD = 0;
		this.cuentaCLP = new Cuenta("CLP", saldoCLP, 200000, 2000);
		this.cuentaUSD = new Cuenta("USD", saldoUSD, 100, 10);
		this.cantidadOpsSesion = 0;
		this.historial = new ArrayList<String>();
		String log = "Cuentas creadas || Saldo CLP: " + saldoCLP + " | Saldo USD: " + saldoUSD;
		agregarRegHistorial(log);
		
	}
	public Usuario(int saldoCLP, int saldoUSD, 
			int montoMaximoRetiroCLP, int montoMaximoRetiroUSD,
			int montoMinimoCLP, int montoMinimoUSD) {
		this.cuentaCLP = new Cuenta("CLP", saldoCLP, montoMaximoRetiroCLP, montoMinimoCLP);
		this.cuentaUSD = new Cuenta("USD", saldoUSD, montoMaximoRetiroUSD, montoMinimoUSD);
		this.cantidadOpsSesion = 0;
		this.historial = new ArrayList<String>();
		String log = "Cuentas creadas || Saldo CLP: " + saldoCLP + " | Saldo USD: " + saldoUSD;
		agregarRegHistorial(log);
	}

	public Cuenta getCuentaCLP() {
		return cuentaCLP;
	}

	public void setCuentaCLP(Cuenta cuentaCLP) {
		this.cuentaCLP = cuentaCLP;
	}

	public Cuenta getCuentaUSD() {
		return cuentaUSD;
	}

	public void setCuentaUSD(Cuenta cuentaUSD) {
		this.cuentaUSD = cuentaUSD;
	}

	public List<String> getHistorial() {
		return historial;
	}
	
	public int getCantidadOpsSesion() {
		return cantidadOpsSesion;
	}
	public void setCantidadOpsSesion(int cantidadOpsSesion) {
		this.cantidadOpsSesion = cantidadOpsSesion;
	}
	public String historialToString() {
		return "\t" + String.join("\n\t", this.historial);
	}
	
	private void agregarRegHistorial(String log) {
		this.historial.add(log);
	}
	
	public void resetCantOps() {
		this.cantidadOpsSesion = 0;
	}
	
	public boolean depositar(String divisa, int monto) {
		boolean estadoOperacion = false;
		// Realizar operación sobre la cuenta respectiva a la divisa
		if (divisa.equals("CLP")) {
			estadoOperacion = this.cuentaCLP.depositar(monto);
		} else if (divisa.equals("USD")) {
			estadoOperacion = this.cuentaUSD.depositar(monto);
		} else {
			System.out.println("La divisa recibida no se reconoce como válida");
			return false;
		}
		// Verificar si la operación fue realizada de forma exitosa
		if (estadoOperacion) {
			//Agregar registro al historial
			String stringMonto = String.valueOf(monto);
			String saldoCLP = String.valueOf(this.cuentaCLP.getSaldo());
			String saldoUSD = String.valueOf(this.cuentaUSD.getSaldo());
			String log = "Depositados " + stringMonto + divisa + " || Saldo CLP: " + saldoCLP + " | Saldo USD: " + saldoUSD;
			agregarRegHistorial(log);
			
			// Aumentar contandor de operaciones
			this.cantidadOpsSesion += 1;
		}
		
		return estadoOperacion;
	}
	
	public boolean retirar(String divisa, int monto) {
		boolean estadoOperacion = false;
		// Realizar operación sobre la cuenta respectiva a la divisa
		if (divisa.equals("CLP")) {
			estadoOperacion = this.cuentaCLP.retirar(monto);
		} else if (divisa.equals("USD")) {
			estadoOperacion = this.cuentaUSD.retirar(monto);
		} else {
			System.out.println("La divisa recibida no se reconoce como válida");
			return false;
		}
		System.out.println("USUARIO estadoOperacion: " + estadoOperacion);
		// Verificar si la operación fue realizada de forma exitosa
		if (estadoOperacion) {
			//Agregar registro al historial
			String stringMonto = String.valueOf(monto);
			String saldoCLP = String.valueOf(this.cuentaCLP.getSaldo());
			String saldoUSD = String.valueOf(this.cuentaUSD.getSaldo());
			String log = "Retirados " + stringMonto + divisa + " || Saldo CLP: " + saldoCLP + " | Saldo USD: " + saldoUSD;
			agregarRegHistorial(log);
			
			// Aumentar contandor de operaciones
			this.cantidadOpsSesion += 1;
		}
		return estadoOperacion;
	}
	
	
	
}

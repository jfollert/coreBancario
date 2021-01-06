package coreBancario;

import java.util.Objects;


public class Cuenta {
	private int saldo;
	private String divisa;
	private final int MONTO_MAXIMO_RETIRO;
	private final int MONTO_MINIMO;
	
	public Cuenta(String divisa, int saldo, int montoMaximoRetiro, int montoMinimo) {
		super();
		this.divisa = divisa;
		this.saldo = saldo;
		this.MONTO_MAXIMO_RETIRO = montoMaximoRetiro;
		this.MONTO_MINIMO = montoMinimo;
	}
	public String getDivisa() {
		return divisa;
	}
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public int getMONTO_MAXIMO_RETIRO() {
		return MONTO_MAXIMO_RETIRO;
	}
	public int getMONTO_MINIMO() {
		return MONTO_MINIMO;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return this.saldo == cuenta.saldo && this.divisa == cuenta.divisa;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.divisa, this.saldo);
    }
    
    private boolean validarMonto(int monto) {
        if (monto < 0) return false; // Verificar que el monto es un número entero positivo
        if (monto < this.MONTO_MINIMO) return false; // Verificar que el monto es mayor al MONTO_MINIMO
        return true;
	}    
	
	public boolean depositar(int monto) {
		// Validar monto según restricciones
		if(!validarMonto(monto)) return false;
        // Verificar overflow de la variable
        try { 
        	this.saldo = Math.addExact(this.saldo, monto);
        }
        catch (ArithmeticException e) {
            return false;
        }
        
        return true;
	}
	
	public boolean retirar(int monto) {
		// Validar monto según restricciones
		if(!validarMonto(monto)) return false;
		if(monto > this.MONTO_MAXIMO_RETIRO) return false; 
		
		if(monto > this.saldo) return false; // Verificar que el monto a retirar no sea mayor al saldo en la cuenta
		this.saldo = this.saldo - monto;
		
		return true;
	}
	
}

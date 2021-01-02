package coreBancario;

import java.util.List;

public class Cuenta {
	private int saldo;
	private List<Registro> registro;
	public Cuenta() {
		super();
	}
	public Cuenta(int saldo, List<Registro> registro) {
		super();
		this.saldo = saldo;
		this.registro = registro;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public List<Registro> getRegistro() {
		return registro;
	}
	public void setRegistro(List<Registro> registro) {
		this.registro = registro;
	}

}

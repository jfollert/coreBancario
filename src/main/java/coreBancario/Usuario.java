package coreBancario;

public class Usuario {
	private int id;
	private Cuenta cuentaCLP;
	private Cuenta cuentaUSD;
	public Usuario() {
		super();
	}
	public Usuario(int id, Cuenta cuentaCLP, Cuenta cuentaUSD) {
		super();
		this.id = id;
		this.cuentaCLP = cuentaCLP;
		this.cuentaUSD = cuentaUSD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}

package coreBancario;

import java.sql.Timestamp;

public class Registro {
	private Timestamp timestamp;
	private int monto;
	
	public Registro() {
		super();
	}

	public Registro(Timestamp timestamp, int monto) {
		super();
		this.timestamp = timestamp;
		this.monto = monto;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}	
	
	
}

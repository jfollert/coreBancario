package coreBancario;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class CoreBancario {

	public static void main(String[] args) throws IOException  
    { 
		while (true) { 
	        BufferedReader reader =  
	                   new BufferedReader(new InputStreamReader(System.in)); 
	        System.out.println("======= Bienvenido a Banco Azul =======");
	        System.out.println("\t \"1\" Dep�sito");
	        System.out.println("\t \"2\" Retiro");
	        System.out.println("\t \"3\" Ver transacciones");
	        System.out.println("\t \"4\" Cerrar sesi�n");
	        
	        System.out.print("Selecciona operaci�n a realizar: ");  
	        String op = reader.readLine(); 
	        
	        if (op.equals("1")) {
	        	System.out.println("Ingrese monto a depositar y moneda, formato (Currency, Amount)");
	        	String deposito = reader.readLine();
	        	System.out.println("Dep�sito exitoso!");
	        	continue;
	        }
	        else if (op.equals("2")) {
	        	System.out.println("Ingrese monto a retirar y moneda, formato (Currency, Amount)");
	        	String deposito = reader.readLine();
	        	System.out.println("Retiro exitoso exitoso!");
	        	continue;
	        }
	        else if (op.equals("3")) {
	        	//IMPLEMENTAR
	        	continue;
	        }
	        else if (op.equals("4")) {
	        	System.out.println("Se ha cerrado la sesi�n, para volver a iniciar sesi�n presione \"ENTER\"");  
	        	reader.readLine(); 
	        	continue;
	        }
	        System.out.println("El valor ingresado debe ser un n�mero entre 1 y 4");
		}
    } 
}

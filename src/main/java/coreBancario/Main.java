package coreBancario;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	private final static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>(); // Almacena los usuarios creados
	
	private static boolean menu(String id) throws IOException 
	{	
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		while (true) { 
			// Verificar que no se haya alcanzado el n�mero m�ximo de operaciones
			boolean maxOpsAlcanzadas = (usuarios.get(id).getCantidadOpsSesion() == 4);
			String op = "";
			if (!maxOpsAlcanzadas){
				System.out.println("\n======= Bienvenido a Banco Azul =======");
		        System.out.println("\t \"1\" Dep�sito");
		        System.out.println("\t \"2\" Retiro");
		        System.out.println("\t \"3\" Ver transacciones");
		        System.out.println("\t \"4\" Cerrar sesi�n");
		        
		        System.out.print("Selecciona operaci�n a realizar: ");
		        op = reader.readLine().trim(); 
			}
	        
	        // Deposito
	        if (op.equals("1") && !maxOpsAlcanzadas) {
	        	System.out.println("Ingrese monto a depositar y moneda, formato (Currency, Amount)");
	        	String input = reader.readLine().trim();
	        	String[] deposito = input.split(" ");
	        	
	        	// Verificar formato deposito e input
	        	if (deposito.length != 2 || input == "") {
	        		System.out.println("Error en el formato recibido, intentelo nuevamente");
	        		continue;
	        	}
	        	String currency = deposito[0];
	        	String stringAmount = deposito[1];
	        	if (!("CLP".equals(currency)) && !("USD".equals(currency))) {
	        		System.out.println("Moneda ingresada inv�lida. Favor utilizar USD o CLP");
	        		continue;
	        	}
	        	int amount;
	        	try {
                    amount = Integer.parseInt(stringAmount);
                }
                catch (Exception e) {
                    System.out.println("Monto ingresado inv�lido. Favor solo ingresar d�gitos");
                    continue;
                }
	        	
	        	boolean estadoOperacion = usuarios.get(id).depositar(currency, amount);
	        	if (!estadoOperacion) {
	        		System.out.println("Dep�sito fallido!");
		        	continue;
	        	}
	        	System.out.println("Dep�sito exitoso!");
	        	continue;

	        }
	        // Retiro
	        else if (op.equals("2") && !maxOpsAlcanzadas) {
	        	System.out.println("Ingrese monto a retirar y moneda, formato (Currency, Amount)");
	        	String input = reader.readLine();
	        	String[] retiro = input.split(" ");
	        	// Verificar formato retiro e input
	        	if (retiro.length != 2 || input == "") {
	        		System.out.println("Error en el formato recibido, intentelo nuevamente");
	        		continue;
	        	}
	        	String currency = retiro[0];
	        	String stringAmount = retiro[1];
	        	if (!("CLP".equals(currency)) && !("USD".equals(currency))) {
	        		System.out.println("Opciones disponibles para Currency: USD, CLP");
	        		continue;
	        	}
	        	int amount;
	        	try {
                    amount = Integer.parseInt(stringAmount);
                }
                catch (Exception e) {
                    System.out.println("El monto ingresado es inv�lido para realizar la operaci�n");
                    continue;
                }
	        	
	        	boolean estadoOperacion = usuarios.get(id).retirar(currency, amount);
	        	if (!estadoOperacion) {
	        		System.out.println("Retiro fallido!");
		        	continue;
	        	}
	        	System.out.println("Retiro exitoso!");
	        	continue;
	        }
	        // Historial de Transacciones
	        else if (op.equals("3") && !maxOpsAlcanzadas) {
	        	System.out.println("\nHistorial de Transacciones: ");
	        	System.out.println("ID Usuario: " + id);
	        	System.out.println(usuarios.get(id).historialToString());
	        	System.out.println("Presiona ENTER para volver");
	        	reader.readLine();
	        	continue;
	        }
	        // Cerrar sesi�n
	        else if (op.equals("4") || maxOpsAlcanzadas ) {
	        	System.out.println("Se ha cerrado la sesi�n, para volver a iniciar sesi�n ingrese \"1\"");  
	        	String input = reader.readLine().trim(); 
	        	if (input.equals("1")) {
	        		usuarios.get(id).resetCantOps();
	        		return true;
	        	} else {
	        		return false;
	        	}
        	
	        }
	        else {
		        System.out.println("Opci�n ingresada inv�lida. Intente nuevamente");
	        }
		}
	}
	
	public static void main(String[] args) throws IOException  
    {	
		boolean mantenerSesion = true; // Variable que mantiene la sesion activa
		while (mantenerSesion) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
			
			System.out.print("Ingrese su ID de usuario para iniciar sesi�n: ");
	        String id = reader.readLine().trim(); 
	        
	        if (id == "" || id.contains(" ")) {
	        	System.out.print("El ID ingresado no es v�lido\n");
	        	continue;
	        }
			
	        // Verificar si el ID de usuario ya se ha utilizado.
	        // Si no se ha utilizado, se generara un nuevo usuario y se agrega al HashMap
	        if (!usuarios.containsKey(id)) { 
	        	usuarios.put(id, new Usuario());
	        }
	        
	       mantenerSesion = menu(id);
	        
		}		
    }
}

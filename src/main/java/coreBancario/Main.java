package coreBancario;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	private final static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>(); // Almacena los usuarios creados
	
	public static void main(String[] args) throws IOException  
    {	
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		
		System.out.print("Ingrese su ID de usuario para iniciar sesión: ");
        String id = reader.readLine(); 
		
        // Verificar si el ID de usuario ya se ha utilizado.
        // Si no se ha utilizado, se generara un nuevo usuario y se agrega al HashMap
        if (!usuarios.containsKey(id)) { 
        	usuarios.put(id, new Usuario());
        } 
        
		while (true) { 
	        System.out.println("\n======= Bienvenido a Banco Azul =======");
	        System.out.println("\t \"1\" Depósito");
	        System.out.println("\t \"2\" Retiro");
	        System.out.println("\t \"3\" Ver transacciones");
	        System.out.println("\t \"4\" Cerrar sesión");
	        
	        System.out.print("Selecciona operación a realizar: ");
	        String op = reader.readLine().trim(); 
	        
	        if (op == "") {
	        	System.out.println("Opción ingresada inválida. Intente nuevamente");
	        	continue;
	        }
	        
	        boolean maxOpsAlcanzadas = (usuarios.get(id).getCantidadOpsSesion() == 4);
	        // Deposito
	        if (op.equals("1") && !maxOpsAlcanzadas) {
	        	System.out.println("Ingrese monto a depositar y moneda, formato (Currency, Amount)");
	        	String input = reader.readLine().trim();
	        	String[] deposito = input.split(" ");
	        	// Verificar input
	        	if (deposito.length != 2) {
	        		System.out.println("Error en el formato recibido, intentelo nuevamente");
	        		continue;
	        	}
	        	String currency = deposito[0];
	        	String stringAmount = deposito[1];
	        	if (!("CLP".equals(currency)) && !("USD".equals(currency))) {
	        		System.out.println("Moneda ingresada inválida. Favor utilizar USD o CLP");
	        		continue;
	        	}
	        	int amount;
	        	try {
                    amount = Integer.parseInt(stringAmount);
                }
                catch (Exception e) {
                    System.out.println("Monto ingresado inválido. Favor solo ingresar dígitos");
                    continue;
                }
	        	
	        	boolean estadoOperacion = usuarios.get(id).depositar(currency, amount);
	        	if (!estadoOperacion) {
	        		System.out.println("Depósito fallido!");
		        	continue;
	        	}
	        	System.out.println("Depósito exitoso!");
	        	continue;

	        }
	        // Retiro
	        else if (op.equals("2") && !maxOpsAlcanzadas) {
	        	System.out.println("Ingrese monto a retirar y moneda, formato (Currency, Amount)");
	        	String input = reader.readLine();
	        	String[] retiro = input.split(" ");
	        	// Verificar input
	        	if (retiro.length != 2) {
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
                    System.out.println("El monto ingresado es inválido para realizar la operación");
                    continue;
                }
	        	
	        	System.out.println("Realizando RETIRO");
	        	System.out.println(amount);
	        	System.out.println(currency);
	        	boolean estadoOperacion = usuarios.get(id).retirar(currency, amount);
	        	System.out.println("estadoOperacion: " + estadoOperacion);
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
	        // Cerrar sesión
	        else if (op.equals("4") || maxOpsAlcanzadas ) {
	        	System.out.println("Se ha cerrado la sesión, para volver a iniciar sesión ingrese \"1\"");  
	        	while (true) {
		        	String input = reader.readLine().trim(); 
		        	if (input.equals("1")) {
		        		usuarios.get(id).resetCantOps();
		        		break;
		        	}
	        	}
	        	continue;
	        }
	        System.out.println("El valor ingresado debe ser un número entre 1 y 4");
		}
		
    }
}

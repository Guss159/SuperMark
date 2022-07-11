package supermark;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;



public class Main {

public static void main(String[] args) throws Exception {
		int choose = 0;
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(stream);
		Usuario usr = Usuario.getInstance();
		do {
			System.out.println("----------------SUPERMARK MERCADO VIRUTAL----------------");
			System.out.println("1 : Ingresear session");
			System.out.println("2 : Regristrarse");
			System.out.println("3 : Ver lista de productos");
			System.out.println("0 : Salir");
			// esto te lleva shopping
			System.out.println("Seleccione una opcion para continuar:");
			choose = Integer.parseInt(buffer.readLine());
			System.out.println("Usted selecciono --> " + choose);
			TimeUnit.SECONDS.sleep(2);
			
			switch(choose) {
				case 1: System.out.println("1 : Ingresear session"); Logger_util.logmein();
				if(usr.getId() != 0) Logger_util.menu_client();

					break;
				case 2: System.out.println("2 : Regristrarse"); Logger_util.registrarUsuario();
							break;
				case 3: System.out.println("3 : Ver lista de productos");
						break; 
			}
			
			
		}while(choose != 0);
		System.out.println("GoodBye!");
		Bdd_conneccion bdd = Bdd_conneccion.getInstance();
		bdd.close();
			
	}
}

/*
 * 
 * comprobante delete session -> move to order 
 * menu if admin show 

 * 
 * -- check stock not select stock0 
 * max 30 articulos (no lo puse en la bdd)
 */

package supermark;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.io.IOException;
import java.io.BufferedReader;

// Noninstantiable utility class

public class Logger_util {

	private Logger_util() {
		throw new AssertionError();
	}

	public static void registrarUsuario() throws IOException, SQLException {
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(stream);
		String username;
		char[] password;
		char[] passwordConfirm;
		Argon2 argon2 = Argon2Factory.create();
		boolean flag = false;
		char slc = 'N';

		// ingresa usuario y contrasenia con opcion de escape
		System.out.println("Ingrese nombre de ususario");
		username = buffer.readLine();
		System.out.println("Ingresado:" + username);
		do {
			System.out.println("Ingrese contraseña");
			password = buffer.readLine().toCharArray();
			System.out.println("Confirme contraseña");
			passwordConfirm = buffer.readLine().toCharArray();

			if (java.util.Arrays.equals(password, passwordConfirm) == false) {
				System.out.println("Las contraseñas no coinciden");
				do {
					System.out.println("Abortar registro? Y/n");
					slc = Character.toUpperCase(buffer.readLine().charAt(0));
				} while (slc != 'Y' && slc != 'N');
			} else
				flag = true;
		} while (flag == false && slc == 'N');

		if (slc != 'Y') { // controla opcion de escape al menu
			Usuario user = Usuario.getInstance();
			// Proteccion GigaChad para la contrasenia
			try {
				String hash = argon2.hash(18, 65536, 1, password);
				user.setPasword(hash);
				System.out.println("hash:"+hash);
			} finally {
				// Wipe confidential data
				argon2.wipeArray(password);
			}
			// se agregarn los datos al singleton Usuario
			// Omito campos, que solo agregan complejidad sin cosas interesantes, por falta
			// de tiempo
			user.setUsername(username);
			System.out.print("\n-->Primer Nombre: ");
			user.setNombre1(buffer.readLine());
			System.out.print("\n-->Primer Apellido: ");
			user.setApellido1(buffer.readLine());
			System.out.print("\n-->DNI: ");
			user.setDni(buffer.readLine());
			String str = null;
			do {
				System.out.print("\n-->Fecha de nacimiento (yyyy-mm-dd): ");
				str = buffer.readLine();
			} while (!str.matches("\\d{4}-\\d{2}-\\d{2}"));
			user.setFecha_nacimiento(str);
			// lo siguiente se simpiflifica simplemente por cuestiones de tiempo
			do {
				System.out.print("\n-->sexo ( M - F - OTRO) ");
				str = buffer.readLine();
			} while (str.equals("M")  && str.equals("F")  && str.equals("OTRO") );
			System.out.print("\n-->Email: ");
			user.setEmail(buffer.readLine());

			// se inserta a la bdd
			user.inserta_Usuario();

		}
	}

	public static void logmein() throws IOException, SQLException {
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(stream);
		char slc = 'N';
		int id = 0;
		String username;
		char[] password;
		ResultSet rs;
		Argon2 argon2 = Argon2Factory.create();

		do {
			System.out.println("Ingrese nombre de ususario");
			username = buffer.readLine();
			System.out.println("Ingresado:" + username);

			System.out.println("Ingrese contraseña");
			password = buffer.readLine().toCharArray();

			try {
				String hash = argon2.hash(18, 65536, 1, password);
				Bdd_conneccion bdd = Bdd_conneccion.getInstance();
				String sql = ("select id from usuario where pasword = '%s';" + hash);
				rs = bdd.querry(sql);
				id = rs.getInt("id");
			} finally {
				// Wipe confidential data
				argon2.wipeArray(password);
			}
			// SQL RS

			if (id == 0) {
				System.out.println("Datos incorrectos");
				do {
					System.out.println("Abortar Login? Y/n");
					slc = Character.toUpperCase(buffer.readLine().charAt(0));
				} while (slc != 'Y' && slc != 'N');
			}

		} while (id == 0 && slc == 'N');

		if (id != 0) {
			Usuario usr = Usuario.getInstance();
			usr.setId(id);
			usr.Update_Usuario();
		}
	}

	public static void logout() {
		Usuario usr = Usuario.getInstance();
		usr.clear();
	}

	public static void menu_client() throws Exception, IOException {
		int choose = 0;
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(stream);
		do {
			System.out.println("----------------Menu---Cliente----------------");
			System.out.println("1 : Mostrar listado de productos");
			System.out.println("2 : Agregar producto al carro");
			System.out.println("3 : Mostrar carro");
			System.out.println("4 : Autorizar compra");
			System.out.println("0 : Salir");

			System.out.println("Seleccione una opcion para continuar:");
			choose = Integer.parseInt(buffer.readLine());
			System.out.println("Usted selecciono --> " + choose);
			TimeUnit.SECONDS.sleep(2);

			switch (choose) {
			case 1:
				System.out.println("1 : Mostrar listado de productos");
				break;
			case 2:
				System.out.println("2 : Agregar producto al carro");
				break;
			case 3:
				System.out.println("3 : Mostrar carro");
				break;
			case 4:
				System.out.println("4 : Autorizar compra");
				break;
			}

		} while (choose != 0);
		Logger_util.logout();
		System.out.println("GoodBye!");
	}

	public static void menu_admin() throws Exception, IOException {
		int choose = 0;
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(stream);

//		prods selected by users (join usser + session items + items?)
//		add new prod 
//		mod prod 
//		mod &add discounts
//		Show ussers taht buy (orders)
	}

}
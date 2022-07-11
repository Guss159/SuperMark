package supermark;

import java.sql.*;

import credenciales.Credenciales;

// esto va a retornar ResulSet que nesesite

public class Bdd_conneccion {

	private static Bdd_conneccion single_bdd_conneccion = null;
	private Connection conn;
	private Statement stmt;
	private String JDBC_DRIVER;
	private String DB_URL;
	private String USER;
	private String PASS;
	private ResultSet rs;

	public static Bdd_conneccion getInstance() {
		if (single_bdd_conneccion == null)
			single_bdd_conneccion = new Bdd_conneccion();

		return single_bdd_conneccion;
	}

	private Bdd_conneccion() {
		Credenciales creds = new Credenciales();
		this.JDBC_DRIVER = creds.getJDBC_DRIVER();
		this.DB_URL = creds.getDB_URL();
		this.USER = creds.getUSER();
		this.PASS = creds.getPASS();
		this.rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to database...");
			this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Creating statement...");
			this.stmt = conn.createStatement();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public void close() {
		try {
			this.rs.close();
			this.stmt.close();
			this.conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.stmt != null)
					this.stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
//		System.out.println("Coneccion_BDD cerrada");
	}

	
	public void update(String sql_string) {
		try {
			this.stmt.executeUpdate(sql_string);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public ResultSet querry(String sql_string) {

		try {
			this.rs = this.stmt.executeQuery(sql_string);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (this.rs != null) {

			}
		}
		return this.rs;
	}

}
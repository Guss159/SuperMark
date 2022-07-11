package supermark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;

public class Usuario {
	private static Usuario single_usuario = null;

	private int id = 0;
	private int id_direccion = 0;
	private int orden_id = 0;

	private String nombre1 = null;
	private String nombre2 = null;
	private String apellido1 = null;
	private String apellido2 = null;
	private String dni = null;
	private String email = null;
	private String celular = null;
	private String telefono_fijo = null;
	private String fecha_nacimiento = null;
	private String sexo = null;

	private String username = null;
	private String pasword = null;

	private Timestamp last_login = null;
	private Timestamp created_at = null;
	private Timestamp modified_at = null;

	// singleton! flag loged? username

	static public Usuario getInstance() {
		if (single_usuario == null)
			single_usuario = new Usuario();

		return single_usuario;
	}

	private Usuario() {

	}

	// add targetaaaa
	// add direccion
	// geo locc

	public void inserta_Usuario() throws SQLException {
		if (id != 0) {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp now = new java.sql.Timestamp(utilDate.getTime());
			this.created_at = now;
			this.modified_at = now;
			String sql = String.format(
					"INSERT INTO usuario (id, email, sexo, nombre1, nombre2, apellido1, apellido2, dni, "
							+ "celular, telefono_fijo,fecha_nacimiento, username, pasword, created_at, modified_at, last_login)"
							+ " VALUES (null, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);",
					this.email, this.sexo, this.nombre1, this.nombre2, this.apellido1, this.apellido2, this.dni,
					this.celular, this.telefono_fijo, this.fecha_nacimiento, this.username, this.pasword,
					this.created_at.toString(), this.modified_at.toString(), this.last_login.toString());
			Bdd_conneccion bdd = Bdd_conneccion.getInstance();
			bdd.update(sql);
			sql = ("select last_insert_id();");
			ResultSet rs = bdd.querry(sql);
			this.id = rs.getInt("id");
		} else
			System.out.println("Este usuario ya se encuentra en la Bdd");
	}

	public void Update_Usuario() {
		String sql;
		Bdd_conneccion bdd = Bdd_conneccion.getInstance();

		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp now = new java.sql.Timestamp(utilDate.getTime());
		this.modified_at = now;
		sql = String.format("INSERT INTO usuario (email, sexo, nombre1, nombre2, apellido1, apellido2, dni, "
				+ "celular, telefono_fijo,fecha_nacimiento, username, pasword, created_at, modified_at, last_login)"
				+ " VALUES ( %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,) where id = %d;", this.email,
				this.sexo, this.nombre1, this.nombre2, this.apellido1, this.apellido2, this.dni, this.celular,
				this.telefono_fijo, this.fecha_nacimiento, this.username, this.pasword, this.modified_at.toString(),
				this.last_login.toString(), this.id);
		bdd.update(sql);

	}

	public void Load_Usuario() throws SQLException {
		String sql;
		Bdd_conneccion bdd = Bdd_conneccion.getInstance();

		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp now = new java.sql.Timestamp(utilDate.getTime());
		this.modified_at = now;
		sql = String.format("select * from usuario where id = %d", this.id);
		ResultSet rs = bdd.querry(sql);

		this.id_direccion = rs.getInt("id_direccion");
		this.nombre1 = rs.getString("nombre1");
		this.nombre2 = rs.getString("nombre2");
		this.apellido1 = rs.getString("apellido1");
		this.apellido2 = rs.getString("apellido2");
		this.dni = rs.getString("dni");
		this.email = rs.getString("email");
		this.celular = rs.getString("celular");
		this.telefono_fijo = rs.getString("telefono_fijo");
		this.fecha_nacimiento = rs.getString("fecha_nacimiento");
		this.sexo = null;

		this.username = null;
		this.pasword = null;

		this.last_login = null;
		this.created_at = null;
		this.modified_at = null;

	}

	public void clear() {
		this.id = 0;
		this.id_direccion = 0;

		this.nombre1 = null;
		this.nombre2 = null;
		this.apellido1 = null;
		this.apellido2 = null;
		this.dni = null;
		this.email = null;
		this.celular = null;
		this.telefono_fijo = null;
		this.fecha_nacimiento = null;
		this.sexo = null;

		this.username = null;
		this.pasword = null;

		this.last_login = null;
		this.created_at = null;
		this.modified_at = null;
	}

	public void starCarrito() throws SQLException {

		if (this.id != 0) {
			if (this.orden_id != 0) {
				Bdd_conneccion bdd = Bdd_conneccion.getInstance();

				java.util.Date utilDate = new java.util.Date();
				java.sql.Timestamp now = new java.sql.Timestamp(utilDate.getTime());
				Timestamp created_at = now;

				String sql = String.format("INSERT INTO orden(id, total, usuario_id, created_at, modified_at) VALUE "
						+ "(null, 0.0, %d, %s, %s)", this.id, created_at, created_at);
				bdd.update(sql);

				sql = ("select last_insert_id();");
				ResultSet rs = bdd.querry(sql);
				this.orden_id = rs.getInt("id");
			} else
				System.out.println("Usuario tiene carrito abierto");
		} else
			System.out.println("Usuario sin Logear");
	}

	public void buyCarrito() throws SQLException, NumberFormatException, IOException {
		if (this.id != 0) {
			if (this.orden_id != 0) {
				InputStreamReader stream = new InputStreamReader(System.in);
				BufferedReader buffer = new BufferedReader(stream);
				Bdd_conneccion bdd = Bdd_conneccion.getInstance();

				java.util.Date utilDate = new java.util.Date();
				java.sql.Timestamp now = new java.sql.Timestamp(utilDate.getTime());
				Timestamp created_at = now;
//				String sql = String.format("INSERT INTO orden(total, modified_at ) "
//						+ "VALUES (null,%d, %f, %s, null, '2022-02-15 13:11:49') where id = %d",  created_at, this.orden_id);
//				bdd.update(sql);

				String sql = String.format("select total from orden where id = %d;", this.orden_id);
				ResultSet rs = bdd.querry(sql);
				int monto = rs.getInt("total");
				
				created_at = now;
				sql = String.format(
						"INSERT INTO pago_detalle(id,id_orden, monto, created_at) "
								+ "VALUES (null, %d, %f, '2022-02-15 13:11:49')",
						this.orden_id, monto, created_at);

				this.orden_id = 0;
				bdd.update(sql);

			} else
				System.out.println("Usuario tiene carrito abierto");
		} else
			System.out.println("Usuario sin Logear");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono_fijo() {
		return telefono_fijo;
	}

	public void setTelefono_fijo(String telefono_fijo) {
		this.telefono_fijo = telefono_fijo;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public Timestamp getLast_login() {
		return last_login;
	}

	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getModified_at() {
		return modified_at;
	}

	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
	}

}

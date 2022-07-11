package credenciales;

public class Credenciales {

	private String JDBC_DRIVER;
	private String DB_URL;

	private String USER;
	private String PASS;
	private String PATH;

	public Credenciales() {
		JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		this.DB_URL = "jdbc:mysql://localhost:3306/world";
		this.USER = "root";
		this.PASS = "admin";
		this.PATH = "C:\\Users\\Laptop\\My-Workspace\\Supermark\\tolog4j.properties";
	}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}

	public void setJDBC_DRIVER(String jDBC_DRIVER) {
		JDBC_DRIVER = jDBC_DRIVER;
	}

	public String getDB_URL() {
		return DB_URL;
	}

	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getPASS() {
		return PASS;
	}

	public void setPASS(String pASS) {
		PASS = pASS;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}
	
}

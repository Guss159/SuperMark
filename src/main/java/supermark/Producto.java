package supermark;


public class Producto {
	private static Producto single_prod = null;

	private int id = 0;
	private int id_categoria = 0;
	private int stock = 0; 
	private String nombre = null;
	private float precio = 0;
	

	public static Producto getInstance() {
		if (single_prod == null)
			single_prod = new Producto();

		return single_prod;
	}
	
	private Producto() {

	}
	
	public void load() {
		
	}
	public void insert() {
		
	}
	
	public void list(){
		
	}
	
}

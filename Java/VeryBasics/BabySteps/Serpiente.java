/*
 * Gerardo M
 * Clase serpiente
 */
public class Serpiente {
	private int clave, tipo;
	private String alim;
	
	public Serpiente(int clave, int tipo, String alim) {
		this.clave=clave;
		this.tipo=tipo;
		this.alim=alim;
	}
	
	public Serpiente(int clave) {//para busqueda
		this.clave=clave;
	}
	
	public int getClave() {
		return clave;
	}
	public int getTipo() {
		return tipo;
	}
	public String getAlim() {
		return alim;
	}
	
	public boolean equals(Object obj) {
		Serpiente otra=(Serpiente)obj;
		return this.clave == otra.clave;
	}
	public String toString() {
		return "\nClave: "+clave+"\nAlimentacion: "+alim+"\nTipo: "+tipo;
	}
	 
	
	
}

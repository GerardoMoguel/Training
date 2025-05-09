package Lista;
/*
 * Gerardo Moguel
 * Clase club, en la que hay una lista ordenada de socios.
 */
public class Club {
	private String nombre;
	private ListaOrdenada<Persona> lista;
	
	public Club(String nombre) {
		this.nombre=nombre;
		lista=new ListaOrdenada();
	}
	
	public boolean altaSocio(String nom,int edad, String BFav) {
		Persona nuevoSocio=new Persona(nom,edad,BFav);
		return lista.agrega(nuevoSocio);
		
	}
	
	public boolean quitaSocio(String nom) {
		Persona aux=new Persona(nom);
		return lista.quita(aux);
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("\nNombre: "+nombre);
		sb.append("\nSocios: "+lista.toString()+"\n");
		return sb.toString();
	}
	
	public static void main (String[]args) {
		Club a=new Club("SPH");
		System.out.println(a.altaSocio("pepe", 12, "margarita"));
		System.out.println(a.altaSocio("ana", 15, "margarita1"));
		System.out.println(a.altaSocio("juan", 14, "margarita2"));

		System.out.println(a.altaSocio("pepe", 12, "margarita"));
		System.out.println(a.quitaSocio("pepe"));
		System.out.println(a.toString());
	}
}

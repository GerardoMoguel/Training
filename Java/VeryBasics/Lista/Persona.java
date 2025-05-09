package Lista;

import java.util.Objects;

/*
 * Gerardo Moguel
 * Clase persona para la clase club.
 */
public class Persona implements Comparable<Persona>{
	private String nombre;
	private int edad;
	private String bebida;
	
	public Persona(String nombre, int edad, String bebida) {
		this.nombre=nombre;
		this.edad=edad;
		this.bebida=bebida;
	}
	
	public Persona(String nombre) {
		this.nombre=nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Socio: "+nombre).append("\nEdad: "+edad).append("\nBebida favorita: "+bebida);
		return sb.toString();
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(bebida, edad, nombre);
	}

	//era necesario que tengas tambie  un equals
	public boolean equals(Object obj) {//necesariamente como la pide java
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public int compareTo(Persona otro) {
		if(this.nombre.compareTo(otro.getNombre())>0) {
			return 1;
		}
		else if(this.nombre.compareTo(otro.getNombre())<0){
			return -1;
		}
		else{
			return 0;
		}
	}
}

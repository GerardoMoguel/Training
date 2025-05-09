/*
 * Clase de ingredientes utilizados en platillos
 */
public class Ingredientes implements Comparable<Ingredientes>{//Ponlo siempre que quieras compareTo en la clase
private String nombre;
private double precio;//precio por unidad / 1 kilo / 1 litro
private String origen;
private int estado;//solido 0, liquido 1, gaseoso 2.

//contructor
public Ingredientes(String nombre,String origen,double precio, int estado) {
	this.nombre=nombre;
	this.origen=origen;
	this.precio=precio;
	this.estado=estado;
}

public Ingredientes(String nombre) {
	this.nombre=nombre;
}

public boolean equals(Object obj) {
	Ingredientes otro=(Ingredientes)obj;
	return this.nombre.equals(otro.nombre);
}

public int compareTo(Ingredientes otro) {
	return this.nombre.compareTo(otro.nombre);
}

//getters y setters
public double getPrecio() {
	return precio;
}

public void setPrecio(double precio) {
	this.precio = precio;
}

public String getNombre() {
	return nombre;
}

public String getOrigen() {
	return origen;
}

public void setOrigen(String origen) {
	this.origen=origen;
}
public int getEstado() {
	return estado;
}

public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append("Ingrediente: "+nombre);
	sb.append("\nPrecio por Kg/L: "+precio);
	sb.append("\nOrigen: "+origen);
	return sb.toString();
}

}

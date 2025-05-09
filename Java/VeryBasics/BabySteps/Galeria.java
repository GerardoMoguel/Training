/*
 * Gerardo Moguel
 * Clase galeria en la que se administran los cuadros y se proporciona el precio
 * Seccion 6, ejercicio 8. C.E.
 */
public class Galeria {
private String autor;
private String obra;
private char tecnica;
private double precio;
private double ancho;
private double alto;
private static int base=1001;
private int clave;

public Galeria() {
	clave=Galeria.base++;
}

public Galeria(String autor, String obra, char tecnica, double precio, double ancho, double alto) {
	this.autor=autor;
	this.obra=obra;
	this.tecnica=tecnica;
	this.precio=precio;
	this.ancho=ancho;
	this.alto=alto;
	clave=Galeria.base++;
}

public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public String getAutor() {
	return autor;
}
public String getObra() {
	return obra;
}
public char getTecnica() {
	return tecnica;
}
public double getAncho() {
	return ancho;
}
public double getAlto() {
	return alto;
}
public int getClave() {
	return clave;
}

public boolean clavesIguales(int clave) {
	boolean resp;
	if(clave==this.clave) {
		resp=true;
		System.out.println("Clave encontrada con exito");
		System.out.println("\nAutor: "+this.getAutor());
		System.out.println("\nObra: "+this.getObra());
		System.out.println("\nTecnica: "+this.getTecnica());
		System.out.println("\nAcho: "+this.getAncho());
		System.out.println("\nAlto: "+this.getAlto());
		System.out.println("\nClave: "+this.getClave());

	}
	else {
		resp=false;
	System.out.println("Clave no encontrada");
	}
	return resp;
}

public void calcularPrecioV() {
	double total=precio;
	if(ancho*alto>14000)
		total=+total*1.1;
	switch(tecnica) {
	case 'O':total=+total*1.25;
		break;
	case 'A':total=+total*1.2;
		break;
	case 'G':total=+total*1.18;
		break;
	default:total=+total*1.1;
	}
	setPrecio(total);
}

public void darDescuento(String autor, double porcentaje) {
	if(this.autor.equals(autor)) {
		double total=precio;
		total=total-total*porcentaje/100;
		setPrecio(total);
	}
} //esto igual esta bien

public void calculaPrecioF(String autor, double porcentaje) {
	double total;
	total=precio;
	switch(tecnica) {
	case 'O':total=+total*1.25;
		break;
	case 'A':total=+total*1.2;
		break;
	case 'G':total=+total*1.18;
		break;
	default:total=+total*1.1;
	}
	if(ancho*alto>14000)
		total=total*1.1;
	
	if(this.autor.equals(autor)) {
		total=total-total*porcentaje/100;
	}
	setPrecio(total);
} //pero este incluye a los dos (calcular precio y determinar descuentos),
//el problema es que siempre pide parametros

public String toString() {
	String cad;
	cad="Titulo"+this.obra;
	return cad;
}
}//class

import java.util.Objects;

/*
 * Gerardo M
 * Clase para administrar las ventas de una compania de bolsas
 * Ejercicio 5, seccion 9 
 */
public class Compania {
private String nombre;
private String direccion;
private String ciudad;
private String director;
double[]ventas;
private int totalVentas;
String[]mes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

public Compania() {
	ventas=new double[12];
	totalVentas=0;
}

public Compania(String nombre, String direccion, String ciudad, String director) {
	this.nombre=nombre;
	this.direccion=direccion;
	this.ciudad=ciudad;
	this.director=director;
	ventas=new double[12];
	totalVentas=0;
}

public String getNombre() {
	return nombre;
}

public String getDireccion() {
	return direccion;
}

public String getCiudad() {
	return ciudad;
}

public String getDirector() {
	return director;
}

public boolean altaDatos(double venta) {
	boolean resp=false;
	if(totalVentas<ventas.length) {
		ventas[totalVentas]=venta;
		totalVentas++;
		resp=true;
	}
	return resp;
}

public double promVentas() {
	double prom;
	prom=ManejadorArreglos.promArr(ventas,totalVentas);
	return prom;
}

public String mesMasV() {
	int pos;
	pos=ManejadorArreglos.indiceMayor(ventas, totalVentas);
	return mes[pos];
}

public String mesMenosV() {
	int pos;
	pos=ManejadorArreglos.indiceMenor(ventas, totalVentas);
	return mes[pos];
}

public int masProm() {
	int total;
	total=ManejadorArreglos.mayorA(ventas, totalVentas, ManejadorArreglos.promArr(ventas, totalVentas));
	return total;
}

public double totalVAnio() {
	double total=0;
	for(int i=0;i<ventas.length;i++) 
		total=total+ventas[i];
	return total;
}

}//class

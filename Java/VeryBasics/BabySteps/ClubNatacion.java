/*
 * Gerardo Moguel
 * Ejercicio 9 seccion arreglos.
 */
public class ClubNatacion {
private String nombre;
private Rectangulo[] albercas;
private int totalAlb;
private final int MAX=10;

public ClubNatacion() {
	albercas= new Rectangulo[MAX];
	totalAlb=0;
}

public ClubNatacion(String nombre, int max) {
	albercas= new Rectangulo[max];
	totalAlb=0;
	this.nombre=nombre;
}

public boolean altaAlberca(double base, double altura) {
	boolean resp=false;
	if (totalAlb<albercas.length) {
		resp=true;
		albercas[totalAlb]=new Rectangulo(base,altura); //asi se construyen objetos en arrays
		totalAlb=totalAlb+1;
	}
	return resp;
}

public double calculaCosto(double mtPrecio) {
	double costo=0;
	for(int i=0;i<totalAlb;i++)
	costo=costo+albercas[i].calculaArea();
	
	costo=costo*mtPrecio;
	return costo;
}

public static void main(String[] args) {
	ClubNatacion uno=new ClubNatacion();
	System.out.println(uno.altaAlberca(2, 5));
	uno.altaAlberca(4, 2);
	uno.altaAlberca(6,10);
	System.out.println(uno.calculaCosto(150));
	
}//main
}//class

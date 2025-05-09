/*
 * clase de oficina de gobierno 
 * ejercicio 2 seccion 9
 * Gerardo Moguel
 */

public class OficinaGobierno {
private int totalEst;
private double []numHab;
private String []nomEst;
private final int MAX=80;//final significa que es una constante, si la remplazamos no se 

//constructores, siempre se crean los arrays en los constructores
public OficinaGobierno() {
	nomEst=new String [MAX];
	numHab=new double [MAX];
	totalEst=0;
}
public OficinaGobierno(int MAX) { //en el caso de que queramos cambiar el max, es a proposito que escribimos MAX, igual
	nomEst=new String[MAX];
	numHab=new double[MAX];
	totalEst=0;
}

public boolean AltaDatos(String estado, double poblacion) {
	boolean resp=false;
	if(totalEst<nomEst.length) {
		nomEst[totalEst]=estado;
		numHab[totalEst]=poblacion;
		totalEst++;
		resp=true;
	}
	return resp;
}

public String EstadoMPoblado() {
	int pos=ManejadorArreglos.indiceMayor(numHab,totalEst);
	return nomEst[pos];
}

public double promedioNacional() {
	double total;
	total=ManejadorArreglos.promArr(numHab, totalEst);
	return total;
}

public int menorProm() {
	int total;
	total=ManejadorArreglos.menorA(numHab, totalEst, ManejadorArreglos.promArr(numHab, totalEst));
	return total;
}

}//main

/*
 * Gerardo Andres Moguel Rovelo
 * Clase para registrar consumo de paniales de una guarderia dependiendo de la edad
 * ejercicio7, seccion9
 */

public class Guarderia {
private String nombre;
private int[]edades;
private int totalNinos;
private final int MAX=20;

public Guarderia(String nombre) {
	this.nombre=nombre;
	edades=new int[MAX];
	totalNinos=0;
}

public boolean altaEdad(int edad) {
	boolean resp=false;
	if(totalNinos<MAX) {
		resp=true;
		edades[totalNinos]=edad;
		totalNinos++;
	}
	return resp;	
}

public int calculaConsumoP() {
	int total=0;
	for(int i=0; i<totalNinos;i++) 
		switch(edades[i]) {
		case 0:total=total+6;
			break;
		case 1:total=total+4;
			break;
		case 2: total=total+3;
			break;
		default: total=total+2;	
		}
	return total;
}

public double promDiarioConsumoP() {
	double promedio=-1;
	if(totalNinos>0) //esto se hace por si no hay datos en el array asi no divide entre 0
		promedio=calculaConsumoP()/totalNinos;
	return promedio;
}

public int encuentraCategMasConsumo() {
	int categoria=-1;
	int []cont= {0,0,0,0};
	for (int i=0;i<totalNinos;i++) {
		cont[edades[i]]=cont[edades[i]]+1;
	}
	
	if(cont[0]*6>cont[1]*4 && cont[0]*6>cont[2]*3 && cont[0]*6>cont[3]*2)
		categoria=0;
	if(cont[1]*4>cont[2]*3 && cont[1]*4>cont[3]*2)
		categoria=1;
	if(cont[2]*3>cont[3]*3)
		categoria=2;
	else
		categoria=3;
	return categoria;
}

}

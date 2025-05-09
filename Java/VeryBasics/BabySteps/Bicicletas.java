/*
 * Gerardo Moguel Rovelo
 * Clase creada para calcular precio de bicicletas
 * Seccion 6, ejericicio 9. C.E.
 */
public class Bicicletas {
private char tipo;
private int accesorios;
private char material;
private static int base=200;
private int id;

public Bicicletas() {
	id=Bicicletas.base++;
}
public Bicicletas(char tipo, char material) {
	this.tipo=tipo;
	this.material=material;
	id=Bicicletas.base++;
}
public Bicicletas(char tipo, int accesorios, char material) {
	this.tipo=tipo;
	this.accesorios=accesorios;
	this.material=material;
	id=Bicicletas.base++;
}

public char getTipo() {
	return tipo;
}
public int getAccesorios() {
	return accesorios;
}
public char getMaterial() {
	return material;
}
public int getId() {
	return id;
}

public double calcularCosto() {
	double costo=0;
	switch(tipo) {
	case 'U': costo=7000;
		break;
	case 'P': costo=15000;
		break;
	case 'M': costo=27000;
		break;
	}
	switch(material) {
	case 'A': costo=costo+2000;
		break;
	case 'F': costo=costo+5000;
		break;	
	}
	if(accesorios>=3 && accesorios<=4)
		costo=costo+600;
	else if (accesorios>4)
		costo=costo+1000;
	return costo;
}

public boolean compararBicis(int clave) {
	boolean resp;
	if(clave==this.getId())
		resp=true;
	else 
		resp=false;
	return resp;
}
}//class

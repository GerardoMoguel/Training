/*
 * Gerardo Moguel Rovelo
 * Seccion 8, ejercicio 2. C.E.
 * Tarjetas de credito y sus funciones
 */
public class Tarjeta {
private int numTar;
private char tipo;
private int categoria;
private int numCuenta;
private int anio;
private int anioVen;
private int nip;
private double limCred;
private double saldo;
//private boolean estado=false; //ta mal
private double comision;
private static int base=12340;
private static int nipBase=1000;

 

/*public Tarjeta() { //esto no porque el minimo que te pide es con los datos
	numCuenta=Tarjeta.base++;
	nip=Tarjeta.nipBase++;
}
*/
public Tarjeta(int numTar,char tipo, int categoria, int anio){
	this.numTar=numTar;
	this.tipo=tipo;
	this.categoria=categoria;
	this.anio=anio;
	numCuenta=Tarjeta.base++;
	nip=Tarjeta.nipBase++;
}

public int getNumTar() {
	return numTar;
}
public char getTipo() {
	return tipo;
}
public int getCategoria() {
	return categoria;
}
public int getNumCuenta() {
	return numCuenta;
}
public int getAnio() {
	return anio;
}
public int getAnioVen() {
	return anioVen;
}
public int getNip() {
	return nip;
}
public double getLimCred() {
	return limCred;
}
public void setLimCred(double limCred) {
	this.limCred = limCred;
}
public double getSaldo() {
	return saldo;
}

public void setNip(int nip) {
	this.nip = nip;
}

public double getComision() {
	return comision;
}
public void setComision(double comision) {
	this.comision = comision;
}

/*public void activaTarjeta(int nip) {
	if(nip==Tarjeta.this.nip) {
		estado=true;
	System.out.println("NIP correcto, cuenta activada");
	}
	else {
		estado=false;
	System.out.println("NIP incorrecto, desactivando cuenta...");
	}
}
*/
public void calculaCredito() {
	double credito=0;
	switch(categoria) {
	case 0: credito= 20000+saldo;
		break;
		
	case 1: credito= 30000+saldo;
		break;
		
	case 2: credito= 50000+saldo;
		break;
	}
	setLimCred(credito);
}

public void calculaComision() {
	double comi=0;
	double total=0;
	switch (tipo) {
	case 'T': if(categoria==2)
		comi=0.5;
		else
			comi=1;
		break;
	case 'A': switch(categoria) {
	case 2: comi=0.05;
		break;
	case 1: comi=0.02;
		break;
	case 0: comi=0.01;
		break;
	}
	}
	total=limCred*comi;
	setComision(total);
}


}//class

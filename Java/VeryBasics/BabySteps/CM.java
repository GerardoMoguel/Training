/*
 * Clase cientifico matematico 
 * Gerardo Moguel
 */
public class CM {
private String nombre;
private String areaEsp;

public CM() {
}

public CM(String nombre, String areaEsp) {
	this.nombre=nombre;
	this.areaEsp=areaEsp;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getAreaEsp() {
	return areaEsp;
}

public void setAreaEsp(String areaEsp) {
	this.areaEsp = areaEsp;
}

//ejercicio 7
public boolean ordenCreciente(int num1, int num2) {
	boolean resp=false;
	if(num1<num2)
		resp=true;
	return resp;
}

//ejercicio 8
public String ordenaCreciente(int num1, int num2) {
	String resp;
	if(num1<=num2)
		resp=num1+"-"+num2;
	else 
		resp=num2+"-"+num1;
	return resp;
}

//ejercicio 12
public double calculaFuncion2(int x) {
	double y=0; //se inicializa 'y' para evitar el error
	
	switch(x%4) {
	case 0: y=Math.pow(x, 2);
			break;
	case 1: y=x/6;
			break;
	case 2: y=Math.sqrt(x);
			break;
	case 3: y=Math.pow(x,3)+5;
			break;
	}
	return y;
}

//ejercicio 9
public boolean esImpar (int num) {
	boolean resp=false;
	if (num%2!=0)
		resp=true;
	return resp;
}

//ejercicio 11
public double calculaFuncion1 (double x) {
	double y=0;
	if (x<=11) {
		y=3*x+36;
	}
	if (x<=33 && x>11) {
		y=Math.pow(x, 2)-10;
	}
	if  (x<=64 && x>33) {
		y=x+6;
	}
	if (x>64) 
		y=0;
	return y;
}

//ejercicio 13

public double calculaFuncion3(int num, int v) {
	double y;
	switch(num) {
	case 1: y=100*v;
			break;
	case 2: y=Math.pow(100, v);
			break;
	case 3: y=100/v;
			break;
	default: y=0;
	}
	return y;
}
}//class

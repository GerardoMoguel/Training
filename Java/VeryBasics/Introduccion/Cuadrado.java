package Introduccion;
/*
 * Gerardo Moguel
 * Clase de cuadrado usada en clase restaurante
 */
public class Cuadrado {
private double lado;

public Cuadrado () {
}

public Cuadrado(double lado) {
	this.lado=lado;
}

public void setLado(double lado) {
	this.lado=lado;
}

public double getLado() {
	return this.lado;
}

public double calculaArea() {
	return lado*lado;
}

public double calculaPerimetro() {
	return lado*4;
}
public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append("Lado: "+lado);
	sb.append("Perimetro: "+this.calculaPerimetro());
	sb.append("Area: "+this.calculaArea());
	return sb.toString();
}

public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cuadrado otro = (Cuadrado) obj;
	return otro.getLado()==(otro.getLado());
}


}

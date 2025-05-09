package Interface;

public class Triangulo implements FigGeometrica{
private double base;
private double lado1;
private double lado2;

public Triangulo(double base, double lado1, double lado2) {
	this.base=base;
	this.lado1=lado1;
	this.lado2=lado2;
}

public double getBase() {
	return base;
}

public double getLado1() {
	return lado1;
}

public double getLado2() {
	return lado2;
}

public double calculaArea() {
	double resp=-1;
	double x=(base+lado1+lado2)/2;
	resp=Math.sqrt(x*(x-base)*(x-lado1)*(x-lado2));
	return resp;
}
public double calculaPerimetro() {
	return base+lado1+lado2;
}

}

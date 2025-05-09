package Interface;

public class Cuadrado extends Cuadrilatero{
private double lado1;

public Cuadrado(double lado1) {
	this.lado1=lado1;
}

public double calculaArea() {
	return Math.pow(lado1, 2);
}

public double calculaPerimetro() {
	return lado1*4;
}

}

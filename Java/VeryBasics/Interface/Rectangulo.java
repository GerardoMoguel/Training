package Interface;

public class Rectangulo extends Cuadrilatero{
	private double lado1;
	private double lado2;
	
	public Rectangulo(double lado1,double lado2) {
		this.lado1=lado1;
		this.lado2=lado2;
	}

	public double calculaArea() {
		return lado1*lado2;
	}

	public double calculaPerimetro() {
		return lado1*2+lado2*2;
	}

}

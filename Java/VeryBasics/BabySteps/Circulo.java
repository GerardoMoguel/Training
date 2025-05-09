
public class Circulo {

	private double radio;
	
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double calculaArea(double radio) {
		double area;
		area=Math.pow(Math.PI*radio,2);
		return area;
	}
	
	public double calculaPerimetro(double radio) {
		double perimetro;
		perimetro=radio*2*Math.PI;
		return perimetro;
	}
	
	public static void main(String[] args) {

	}//main

}//class

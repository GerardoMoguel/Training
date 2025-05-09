/*
 * Gerardo Moguel
 * 
 */
public class Rectangulo {
	
	private double altura;
	private double base;
	
	public Rectangulo () {
		
	}
	
	public Rectangulo (double altura, double base) {
		this.altura=altura;
		this.base=base;
}
	
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	
	public double calculaArea() {
		double area=altura*base;
		return area;
	}
	
	public double calculaPerimetro() {
		double perimetro;
		perimetro=(altura+base)*2;
		return perimetro;
	}
	
	
	
	public String toString() {
		return "El rectangulo tiene una base de: "+base+"/nY una altura de: "+altura;
	}

	public static void main(String[] args) {

	new Rectangulo(20,30);
	
		
	}//main

}//class

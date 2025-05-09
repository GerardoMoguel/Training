package Interface;

public class Circulo implements FigGeometrica{
private double radio;

public Circulo(double radio) {
	this.radio=radio;
}

public double calculaArea() {
	return Math.pow((radio), 2)*Math.PI;
}

public double calculaPerimetro() {
	return Math.PI*radio*2;
}

public double getRadio() {
	return radio;
}

public String toString() {
	StringBuilder sb= new StringBuilder();
	sb.append("Radio: ").append(radio);
	sb.append("\nArea: ").append(calculaArea());
	sb.append("\nPerimetro: ").append(calculaPerimetro());
	return sb.toString();
}

}

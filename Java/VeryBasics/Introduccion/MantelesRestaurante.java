package Introduccion;
/*
 * Gerardo Moguel;
 * Clase para calcular la cantidad de tela que
 * tiene que usar un restaurante para crear los manteles
 * de sus mesas
 */
public class MantelesRestaurante {
private String nom;
private double ladoM;

public MantelesRestaurante(String nom,double lado) {
this.nom=nom;
this.ladoM=lado;
}

public double cantTela(int mesasDisp) {
	double resp=-1;
	if(mesasDisp>=1 && mesasDisp<=25) {
		Cuadrado uno=new Cuadrado(this.ladoM);
		resp=uno.calculaArea()*mesasDisp;
	}
	return resp;
}

public double cantHilo(int mesasDisp) {
	double resp=-1;
	if(mesasDisp>=1 && mesasDisp<=25) {
		Cuadrado uno=new Cuadrado(this.ladoM);
		resp=uno.calculaPerimetro()*mesasDisp;
	}
	return resp;
}

}

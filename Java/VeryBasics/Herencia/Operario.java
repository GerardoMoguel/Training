package Herencia;

public class Operario extends Empleado{
private int horasExtras;

public Operario () {
	super();
}
public Operario(int clave) {
	super(clave);
}

public Operario(String nombreEmpleado, double salarioBase, int horasExtras) {
	super(nombreEmpleado,salarioBase);
	this.horasExtras=horasExtras;
}

public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append(super.toString());
	sb.append("\nHoras extras: "+horasExtras);
	return sb.toString();
}

public double calculaSalario(double prestac, double deduc, double precioHE) {
		return calculaSalario(prestac,deduc)+horasExtras*precioHE;
}

public double calculaSalario(double prestac,double deduc) {
	return sueldoBase+(sueldoBase*prestac/100)-sueldoBase*deduc/100;
}

public static void main (String[]str) {
	Operario uno=new Operario("Gerardo",200,13);
	System.out.println(uno.toString());
	double a=uno.calculaSalario(300, 10);
	System.out.println(a);
}
}

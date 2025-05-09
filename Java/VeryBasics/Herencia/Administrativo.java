package Herencia;

public class Administrativo extends Empleado {
private String departamento;
private String telefono;

public Administrativo() {
	super();
}

public Administrativo (int clave) {
	super(clave);
}

public Administrativo(String nombreEmpleado, double sueldoBase, String departamento, String telefono) {
	super(nombreEmpleado,sueldoBase);
	this.departamento=departamento;
	this.telefono=telefono;
}

public double calculaSalario(double prestac,double deduc) {
	return sueldoBase+(sueldoBase*prestac/100)-sueldoBase*deduc/100;
}

public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append(super.toString());
	sb.append("\nDepartamento: "+ departamento);
	sb.append("\nTelefono: "+telefono);
	return sb.toString();
}
}

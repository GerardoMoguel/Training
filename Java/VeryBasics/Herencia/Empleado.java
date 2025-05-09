/*
 * Gerardo Andres Moguel Rovelo
 * Clase general de empleado, padre de la herencia
 */
package Herencia;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Empleado {
private int claveEmpleado;
private String nombreEmpleado;
protected double sueldoBase;
private static int serie=100;

protected Empleado() {
	this.claveEmpleado=serie;
	serie++;
}

protected Empleado(int clave) {
	this.claveEmpleado=clave;
}

protected Empleado(String nom, double sueldo) {
	this();
	this.nombreEmpleado=nom;
	this.sueldoBase=sueldo;
}

public double getSueldoBase() {
	return this.sueldoBase;
}

public String getNombreEmpleado() {
	return this.nombreEmpleado;
}

public int getClave() {
	return this.claveEmpleado;
}
public void setSueldoBase(double porcentaje) {
	this.sueldoBase+=sueldoBase*porcentaje/100;
}
public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append("Nombre de empleado: "+nombreEmpleado);
	sb.append("\nClave de empleado: "+claveEmpleado);
	sb.append("\nSueldo base: "+sueldoBase);
	return sb.toString();
}


public boolean equals(Object obj) {
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Empleado other = (Empleado) obj;
	return claveEmpleado == other.claveEmpleado;
}


public abstract double calculaSalario(double prestac, double deduc);

}

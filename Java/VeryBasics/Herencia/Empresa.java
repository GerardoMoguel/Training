/*
 * Gerardo Moguel
 * clase de empresa
 */
package Herencia;

public class Empresa {
private String nom;
private String dueno;
private String direccion;
private Empleado[]emp;
private int total;
private final int MAX=50;

public Empresa(String nom, String dueno, String direc) {
	this.nom=nom;
	this.dueno=dueno;
	this.direccion=direc;
	emp=new Empleado[MAX];
	total=0;
}




}

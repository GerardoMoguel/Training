/*
 * Gerardo Moguel 
 * Clase de alumno
 */
package Introduccion;

public class Alumno {
	
private static int serie=100;
private int claveUnica;
private String nomAlum;
private double[] calif;
private int totalCalif;
private int totalCalA;
private final int MAX_CALIF=50;

public Alumno(String nom) {
	this.nomAlum=nom;
	calif= new double [MAX_CALIF];
	totalCalif=0;
	totalCalA=0;
	claveUnica=serie;
	serie++;
}

public Alumno(int clave) {
	this.claveUnica=clave;
}

public boolean altaCalif (double cal) {
	boolean resp=false;
		if(totalCalif<MAX_CALIF && cal>0) {
			calif[totalCalif]=cal;
			totalCalif++;
			if(cal>=6) {
				totalCalA++;
			}
			resp=true;
		}
	return resp;
}

public String getNomAlum() {
	return this.nomAlum;
}

public int getClaveUnica() {
	return this.claveUnica;
}

public int getAprovadas() {
	return this.totalCalA;
}

public int getTotalCalif() {
	return this.totalCalif;
}

public boolean equals(Object obj) {
	if(obj==null)
		return false;
	Alumno otro=(Alumno)obj;
	return this.claveUnica==otro.claveUnica;
}

public boolean equalsNom(String obj) {
	if(obj==null)
		return false;
	Alumno otro=new Alumno(obj);
	return this.nomAlum.equals(otro.nomAlum);
}

public double calculaProm() {
	double resp=-1;
	if(totalCalif>0) {
		double total=0;
		for(int i=0;i<totalCalif;i++) {
			total+=calif[i];
		}
		resp=total/totalCalif;
	}
	return resp;
}

public String toString() {
	StringBuilder sb=new StringBuilder(); //cambiar gets
	sb.append("Nombre alumno: "+this.getNomAlum());
	sb.append("\nClave unica: "+this.getClaveUnica());
	sb.append("\nMaterias cursadas: "+this.getTotalCalif());
	sb.append("\nMaterias reprobadas: "+(this.getTotalCalif()-this.getAprovadas()));
	sb.append("\nPromedio: "+this.calculaProm()+"\n");

	return sb.toString();
}

}

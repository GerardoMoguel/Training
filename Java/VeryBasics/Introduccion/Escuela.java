/*
 * Gerardo Moguel
 * Clase de escuela que tiene array de alumnos, sobre los cuales realiza
 * varias funciones y metodos.
 */
package Introduccion;

import java.util.ArrayList;

public class Escuela {
	private String nombre;
	private String direccion;
	private Alumno[] alumnos;
	private final int MAX_ALUMS=50;
	private int totalAlums;
	
	public Escuela(String nom, String direc) {
		this.nombre=nom;
		this.direccion=direc;
		alumnos=new Alumno[MAX_ALUMS];
		totalAlums=0;
	}
	
	public boolean altaAlum(String nom) {
		boolean resp=false;
		if(totalAlums<MAX_ALUMS) {
			alumnos[totalAlums]=new Alumno(nom);
			totalAlums++;
			resp=true;
		}
		return resp;
	}
	
	public boolean altaCalif(double cal, int claveU) {
		boolean resp=false;
		int pos;
		pos=buscaAlum(claveU);
		if(pos>=0) {
			resp=alumnos[pos].altaCalif(cal);
		}
		return resp;
	}
	
	private int buscaAlum(int clave) {
		Alumno a=new Alumno(clave);
		int i=0;
		while(i<totalAlums && !a.equals(alumnos[i])){
			i++;
		}
		if(i==totalAlums) {
			i=-1;
		}
		return i;
	}
	public String datosAlumno(int clave) {
		int pos;
		String resp=null;
			pos=buscaAlum(clave);
			if(pos>=0) {
				resp=alumnos[pos].toString();
			}
		return resp;
	}
	
	public String nomPromAlums() {
		String resp=null;
		if(totalAlums>0) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<totalAlums;i++) {
				sb.append("\nNombre: "+alumnos[i].getNomAlum()+"\nPromedio: "+alumnos[i].calculaProm()+"\n");
			}
			resp=sb.toString();
		}
		return resp;
	}
	
	public String masAprobadas() {
		String resp=null;
		if(totalAlums>0) {
			int max=-1;
			for(int i=0;i<totalAlums;i++) {
				if(alumnos[i].getAprovadas()>max) {
					max=alumnos[i].getAprovadas();
					resp=alumnos[i].getNomAlum();
				}
			}
		}
		return resp;
	}
	
	public ArrayList<String> masAprobadasEtc() {
		ArrayList<String> a=new ArrayList<String>();
		if(totalAlums>0) {
			int max=-1;
			for(int i=0;i<totalAlums;i++) {
				if(alumnos[i].getAprovadas()>max) {
					max=alumnos[i].getAprovadas();
				}
			}
			for(int j=0;j<totalAlums;j++) {
				if(alumnos[j].getAprovadas()==max) {
					a.add(alumnos[j].getNomAlum());
				}
			}
			return a;
		}
		else
			return null;
	}
	
	public ArrayList<String> masAprobadasEtcDos() { //la que hicimos en clase
		ArrayList<String> a;
		if(totalAlums>0) {
			int max=-1;
			a=new ArrayList<String>();
			for(int i=0;i<totalAlums;i++) {
				if(alumnos[i].getAprovadas()>max) {
					max=alumnos[i].getAprovadas();
					a.clear();
					a.add(alumnos[i].getNomAlum());
				}
				else {
					if(alumnos[i].getAprovadas()==max) {
						a.add(alumnos[i].getNomAlum());
					}
				}
			}
		}
		else
			a=null;
		return a;
	}	
	public int getClaveUnica(String nom) {
		int resp=-1;
		if(totalAlums>0) {
			int i=0;
			while(i<totalAlums && !alumnos[i].getNomAlum().equals(nom)) {
				i++;
			}
			if(i!=totalAlums) {
				resp=alumnos[i].getClaveUnica();
			}
		}
		return resp;
	}
	
	public static void main(String[] args) {
		Escuela uno=new Escuela("ITAM", "Rio Hondo");
		uno.altaAlum("Pepe");
		uno.altaCalif(6,uno.getClaveUnica("Pepe"));
		uno.altaCalif(7,100);
		uno.altaCalif(7,100);
		uno.altaCalif(8,100);
		uno.altaCalif(3,100);


		uno.altaAlum("Maria");
		uno.altaCalif(8,101);
		uno.altaCalif(10,101);
		uno.altaCalif(9,101);
		uno.altaCalif(8,101);
		uno.altaCalif(3,101);
		uno.altaCalif(8,101);

		uno.altaAlum("Aldo");
		uno.altaCalif(9,102);
		uno.altaCalif(10,102);
		uno.altaCalif(8,102);
		uno.altaCalif(8,102);
		uno.altaCalif(6,102);
		uno.altaCalif(2,102);
		
		System.out.println(uno.datosAlumno(uno.getClaveUnica("Pepe")));
		System.out.println(uno.datosAlumno(102));

		System.out.println(uno.nomPromAlums());
		System.out.println(uno.masAprobadas());
		System.out.println(uno.masAprobadasEtc().toString());
	}
}

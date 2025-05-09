package Conjuntos;

import java.util.Iterator;

public class Conjuntos {
	public static void main(String[]args) {
		ConjuntosADT<String>colores=new ConjuntoA();
		ConjuntosADT<String>meses=new ConjuntoA();
		
		colores.agrega("Verde");
		colores.agrega("Verde");
		colores.agrega("Verde");
		colores.agrega("Verde");
		colores.agrega("Verde");
		colores.agrega("Verde");

		System.out.println("/nConjunto de colores: "+colores);
		colores.agrega("Naranja");
		colores.agrega("Amarillo");
		colores.agrega("Gris");
		
		meses.agrega("Julio");
		meses.agrega("Abril");
		meses.agrega("Marzo");
		System.out.println("\nUnion de los conjuntis: "+colores.union(meses));
		
		
		//usando while
		int suma=0;
		Iterator<String>it=colores.iterator();
		while(it.hasNext()) {
			suma+=it.next().length();
		}
		if(colores.getCardinalidad()>0) {
			System.out.println("\nPromedio: "+suma/colores.getCardinalidad());
		}
		
		//usando for each / for all
		for (String cad: colores) {
			suma+=cad.length();
		}
		if(colores.getCardinalidad()>0) {
			System.out.println("\nPRomedio: "+suma/colores.getCardinalidad());
		}
	}
}

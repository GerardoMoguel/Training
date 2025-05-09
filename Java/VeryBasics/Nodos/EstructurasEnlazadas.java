package Nodos;

import java.util.Iterator;

public class EstructurasEnlazadas{

	public static void main (String[]args) {
		EE<Integer> e1=new EE();
		int suma=0;
		boolean flag=false;
		e1.agregaFinal(1);
		e1.agregaFinal(2);
		e1.agregaFinal(3);
		e1.agregaFinal(4);
		e1.agregaFinal(5);
		e1.agregaFinal(6);
		
		System.out.println("\n"+e1);
		for(Integer n: e1) {
			suma+=n;
		}
		Iterator<Integer> it=e1.iterator();
		while(it.hasNext()&& !flag) {
			flag=it.next().equals(8);
		}
		if(flag) {
			System.out.println("\nSi esta el 8");
		}
		else {
			System.out.println("No esta el 8");
		}
	}
}

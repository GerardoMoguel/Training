package Pila;

import java.util.ArrayList;

public class InvierteElementos {
	public static <T>void invertir(PilaADT pila) {
		ArrayList list=new ArrayList();
		while(!pila.isEmpty()) {
			list.add(pila.pop());
		}
		for(int i=0;i<list.size();i++) {
			pila.push(list.get(i));
		}
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lista;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author estephaniamartinez
 */
public class IteradorLista <T> implements Iterator<T>{
	private NodoDoble <T> actual;
	
	public IteradorLista(NodoDoble<T> actual) {
		this.actual=actual;
	}
	
	public boolean hasNext() {
		return actual!=null;
	}

	public T next() {
		if(hasNext()) {
		T dato=actual.getDato();
		actual=actual.getSig();
		return dato;
		}
		throw new NoSuchElementException();
	}
}

    


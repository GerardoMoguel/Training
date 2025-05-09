package Nodos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorEE <T> implements Iterator<T>{
	private Nodo<T> actual;
	
	public IteradorEE(Nodo<T>actual) {
		this.actual=actual;
	}
	
	public boolean hasNext() {
		return actual!=null;
	}

	public T next() {
		if(hasNext()) {
		T resp=actual.getDato();
		actual=actual.getDir();
		return resp;
		}
		throw new NoSuchElementException();
	}
}

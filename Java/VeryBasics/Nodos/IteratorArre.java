package Nodos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorArre <T>implements Iterator<T>  {
	private T[]coleccion;
	private int total;
	private int actual;
	
	public IteratorArre (T[] coleccion, int total) {
		this.coleccion=coleccion;
		this.total=total;
		actual=0;
	}

	@Override
	public boolean hasNext() {
		return actual<total;
	}

	@Override
	public T next() {
		if(hasNext()) {
			T resultado=coleccion[actual];
			actual++;
			return resultado;
		}
		throw new NoSuchElementException();
	}
	
}

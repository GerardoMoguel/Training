package Nodos;

public class ColaEE <T> implements ColaADT <T>{
	private EE<T> node;
	
	public ColaEE() {
		node=new EE();
	}

	public void agrega(T dato) {	
		node.agregaFinal(dato);
	}

	public T quita() {
		if(node.estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		return node.quitaPrimerDato();
	}

	public boolean estaVacia() {
		return node.estaVacia();
	}

	public T primero() {
		if(node.estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else {
			return node.getPrimero().getDato();
		}
	}
	
	public T ultimo() {
		if(estaVacia()) {
			throw new RuntimeException("Esta vacia");
		}
		else {
			return (T)node.getUltimo();
		}
	}
}

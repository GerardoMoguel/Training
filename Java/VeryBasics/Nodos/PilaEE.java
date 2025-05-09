package Nodos;

public class PilaEE <T >implements PilaADT<T>{
	private EE<T>node;
	private int tope;
	
	public PilaEE() {
		node=new EE();
		tope=-1;
	}
	
	public boolean isEmpty() {
		return tope==-1;
	}
	

	public void push(T dato) {
		node.agregaFinal(dato);
		tope++;
	}

	public T pop() {
		tope--;
		return node.quitaUltimoDato();
	}

	public T peek() {
		if(isEmpty()) {
			throw new ExceptionColeccionVacia("La pila esta vacia");
		}
		return (T) node.getUltimo();
	}
	
	public String toString() {
		return node.toString();
	}
}

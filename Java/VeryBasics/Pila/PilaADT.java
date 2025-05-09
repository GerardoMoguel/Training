package Pila;

public interface PilaADT <T>{//ADT=Abstract Data Type
	public void push (T dato);
	public T pop();
	public boolean isEmpty();
	public T peek();
}

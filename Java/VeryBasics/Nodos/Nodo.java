package Nodos;

public class Nodo <T>{
	private T dato;
	private Nodo<T> dir;
	
	public Nodo(T dato) {
		this.dato=dato;
		dir=null;
	}
	
	public T getDato() {
		return dato;
	}
	
	public void setDato(T dato) {
		this.dato=dato;
	}
	
	public Nodo<T> getDir(){
		return dir;
	}
	
	public void setDir(Nodo<T> nodo) {
		this.dir=nodo;
	}
	
	public String toString() {
		return dato+"\n";
	}
}

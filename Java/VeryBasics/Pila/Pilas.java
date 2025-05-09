package Pila;

public class Pilas {

	public static void main(String[]args) {
		PilaA<String> p =new PilaA();
		try { //por si la pila esta vacia
			System.out.println("Dato"+p.pop());
		}catch (Exception error) {
			System.out.println("Intentaste quitar en pila vacia");
		}
	}
}

package Colas;
//Gerardo Andres Moguel Rovelo
public class Ejercicios <T> extends ColaA<T>{
	//Ejercicio 35, invierte elementos de una estructura Cola Generica
	//Solucion iterativa.
	public static <T> T[] invierteCola(ColaA cola) {
		T inv[]=(T[])new Object[cola.getCola().length];
		if(cola.getFin()!=cola.getFrente()) {//que no este vacia o 1 dato
			int i=0;
			while(!cola.estaVacia()) {
				inv[i]=(T) cola.quitaInv();
				i++;
			}
			return (T[]) inv;
		}
		else {
			throw new RuntimeException("Esta vacia");
		}
	}
	
	//Solucion recursiva.
	public static <T>T[] invierteColaRecursivo(ColaA cola) {
		T inv[]=(T[])new Object[cola.getCola().length];
		if(cola.getFin()!=cola.getFrente()) {//que no este vacia o 1 dato
			int i=0;
			
			invierteColaRecursivo(inv,cola,i);
			return (T[]) inv;
		}
		else {
			throw new RuntimeException("Esta vacia");
		}
	}
	
	public static <T> T[] invierteColaRecursivo(T[] inv, ColaA cola,int i) {
		if(!cola.estaVacia()) {
			inv[i]=(T) cola.quitaInv();
			return invierteColaRecursivo(inv,cola,i+1);
		}
		else {
			return inv;
		}
	}
	public static void main (String[]args) {
		
		//Prueba solucion iterativa
		ColaA test= new ColaA();
		test.agrega(1);
		test.agrega(2);
		test.agrega(3);
		test.agrega(4);
		System.out.println(test.getCola()[0]);
		System.out.println(test.getCola()[1]);
		System.out.println(test.getCola()[2]);
		System.out.println(test.getCola()[3]);
		Object[] arr=new Object[4];
		arr=invierteCola(test);
		System.out.println("\n"+arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		
		//Prueba solucion recursiva.
		ColaA test1= new ColaA();
		test1.agrega(6);
		test1.agrega(7);
		test1.agrega(8);
		test1.agrega(9);
		System.out.println("\n"+test1.getCola()[0]);
		System.out.println(test1.getCola()[1]);
		System.out.println(test1.getCola()[2]);
		System.out.println(test1.getCola()[3]);
		Object[] arr1=new Object[4];
		arr1=invierteColaRecursivo(test1);
		System.out.println("\n"+arr1[0]);
		System.out.println(arr1[1]);
		System.out.println(arr1[2]);
		System.out.println(arr1[3]);
	}
}

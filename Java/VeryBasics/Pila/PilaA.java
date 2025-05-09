package Pila;

public class PilaA <T >implements PilaADT<T>{
	private T[]pila;
	private int tope;
	private final int MAX_PILA=20;
	
	public PilaA() {
		pila=(T[]) new Object[MAX_PILA];//no se puede poner solo new T dato, tienes que forzarlo a ser un objeto
		tope=-1;
	}
	
	public PilaA(int MAX_PILA) {
		pila=(T[]) new Object[MAX_PILA];//no se puede poner solo new T dato, tienes que forzarlo a ser un objeto
		tope=-1;
	}
	public boolean isEmpty() {
		return tope==-1;
	}


	@Override
	public void push(T dato) {
		if(tope==pila.length-1) {
			expande();
		}
		tope++;
		pila[tope]=dato;
	}

	public void expande() {
		T[]masGrande=(T[]) new Object[pila.length*2];
		for(int i=0;i<pila.length;i++) {
			masGrande[i]=pila[i];
			pila=masGrande;//guarda la direccion de masGrande en la variable pila
		}
	}

	@Override
	public T pop() {
		if(isEmpty()) {
			throw new ExceptionColeccionVacia("La pila esta vacia");
		}
		T resp=pila[tope];
		pila[tope]=null;
		tope--;
		return resp;
	}

	@Override
	public T peek() {
		if(isEmpty()) {
			throw new ExceptionColeccionVacia("La pila esta vacia");
		}
		return pila[tope];
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<=tope;i++) {
			sb.append(pila[i]+"\n");
		}
		return sb.toString();
	}

}
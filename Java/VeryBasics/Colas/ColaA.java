package Colas;

public class ColaA <T> implements ColaADT <T>{
	private T cola[];
	protected int frente;
	protected int fin;
	private final int MAX=20;
	
	public ColaA() {
		cola=(T[])new Object[MAX];
		frente=-1;
		fin=-1;
	}
	
	public ColaA(int MAX) {
		cola=(T[])new Object[MAX];
		frente=-1;
		fin=-1;
	}
	
	public T[] getCola() {
		return cola;
	}
	
	public int getFin() {
		return fin;
	}
	
	public int getFrente() {
		return frente;
	}
	
	
	public void aumentaCapacidad() {
		T[] colaX= (T[])(new Object[cola.length*2]);
		int i=0;
		while(!estaVacia()) {
			colaX[i]=quita();
			i++;
		}
		cola=colaX;
		frente=0;
		fin=i-1;
	}
	
	public void agrega(Object dato) {	
		if((fin+1)%cola.length==frente) {//cola llena
			aumentaCapacidad();
		}
		else {
			if(estaVacia()) {//cola vacia
				frente=0;
		}
			fin=(fin+1)%cola.length;
			cola[fin]=(T) dato;
		}
	}

	public T quita() {
		T resp;
		if(!estaVacia()) {
			resp=cola[frente];
			cola[frente]=null;
			if(frente==fin) {
				frente=-1;
				fin=-1;
			}
			else {
				frente=(frente+1)%cola.length;
			}
		}
		else {
			throw new RuntimeException("Esta vacia");
		}
		return resp;
	}
	
	public T quitaInv() {
		T resp;
		if(!estaVacia()) {
			resp=cola[fin];
			if(frente==fin) {
				frente=-1;
				fin=-1;
			}
			else {
				fin=(fin-1)%cola.length;
			}
		}
		else {
			throw new RuntimeException("Esta vacia");
		}
		return resp;
	}

	public boolean estaVacia() {
		return frente==-1;
	}

	public T primero() {
		try {
			return cola[frente];
		}catch (Exception e){
			throw new RuntimeException("No hay datos en la cola");
		}
	}
	
	public T primeroInv() {
		try {
			return cola[fin];
		}catch (Exception e){
			throw new RuntimeException("No hay datos en la cola");
		}
	}
	
	public T ultimo() {
		if(estaVacia()) {
			throw new RuntimeException("Esta vacia");
		}
		T resp= cola[fin];
		return resp;
	}

}

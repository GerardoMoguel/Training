package Recursividad;

public class ArregloGenerico<T extends Comparable <T>> {
	private T[]arreglo;
	private final int MAX=50;
	private int total;
	
	public ArregloGenerico() {
		arreglo=(T[])new Comparable[MAX]; //instanciar arreglos genericos
		total=0;
	}
	
	public ArregloGenerico(int MAX) {
		arreglo=(T[])new Comparable[MAX];
		total=0;
	}
	
	public boolean alta(T dato) {
		boolean resp=false;
		if(total<MAX) {
			arreglo[total]=dato;
			resp=true;
			total++;
		}
		return resp;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		return toString(0,sb);
	}
	
	private String toString(int i,StringBuilder sb) {
		if(i==total) {
			return sb.toString();
		}
		else {
			sb.append(arreglo[i]+" ");
			return toString(i+1,sb); //esto regresa el metodo con +1 en la i y con el sb con el nuevo dato
		}
	}
	
	public boolean eliminaDatos(T dato) {
		boolean resp=false;
		return eliminaDatos(dato,0,resp);
	}
	private boolean eliminaDatos(T dato, int i, boolean resp) {
		if(i==total) {
			return resp;
		}
		else {
			if(dato.equals(arreglo[i])) {
				arreglo[i]=arreglo[total-1];
				arreglo[total-1]=null;
				total--;
				resp=true; 
				i--; //para que igual analice el dato que se cambia de la ultima casilla.
			}
			return eliminaDatos(dato, i+1,resp);
		}
	}
	
	public int busquedaBinaria(T dato) {
		int izq=0,der=total-1,mid=(der+izq)/2;
		return busquedaBinaria(izq,der,mid,dato);
	}
	
	public int busquedaBinaria(int izq, int der, int mid, T dato) {
		if(izq>der) {
			mid=-(izq+1);
			return mid;
		}
		else if(dato.equals(arreglo[mid])) {
			return mid;
		}
		else {
			if(dato.compareTo(arreglo[mid])>0) {
				izq=mid+1;
			}
			else {
				der=mid-1;
			}
			mid=(izq+der)/2;
			return busquedaBinaria(izq,der,mid,dato); 
		}
		
	}
	
	public void ordenarArr() {
		T menor=arreglo[0];
		int pos=0;
		ordenarArr(0,pos,menor);
	}
	
	private void ordenarArr(int i, int pos, T menor) {
		if(i<total) {
			int j=i+1;
			if(j<total) {
				if(arreglo[i].compareTo(arreglo[j])<0) {
					menor=arreglo[i];
					arreglo[i]=arreglo[j];
					arreglo[j]=arreglo[i];
				}//no esta acabado
				
			}
		}
	}
	
	public static void main (String[]args) {
		ArregloGenerico<Integer> a=new ArregloGenerico();
		a.alta(2);
		a.alta(3);
		a.alta(4);
		int resp=a.busquedaBinaria(2);
		System.out.println(resp);
		System.out.println(a.eliminaDatos(2));
		int resp1=a.busquedaBinaria(2);
		System.out.println(resp1);
	}
	
	
}

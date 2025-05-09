package Nodos;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Gerardo M
 * Clase de estructuras enlazadas o sea nodos.
 */
public class EE <T> implements Iterable<T>{
	
	private Nodo<T> primero;
	
	public EE() {
		primero=null;
	}
	
	public boolean estaVacia() {
		return primero==null;
	}
	
	public Nodo<T> getPrimero(){ //metodo para colaEE
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else
		return primero;
	}
	
	public Nodo<T> getUltimo(){ //metodo para colaEE
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		Nodo<T> resp=null;
		if(primero.getDir()==null) {
			resp=(Nodo<T>) primero.getDato();
		}
		else {
			Nodo<T>actual=primero.getDir();
			while(actual.getDir()!=null) {
				actual=actual.getDir();
			}
			resp=(Nodo<T>)actual.getDato();
		}
		return resp;
	}
	
	public void agregaInicio(T dato) {
		Nodo<T>nuevo=new Nodo(dato);
		nuevo.setDir(primero);
		primero=nuevo;
	}
	
	public void agregaFinal(T dato) {
		if(estaVacia()) {
			agregaInicio(dato);
		}
		else {
			Nodo<T>actual=primero;
			while(actual.getDir()!=null) {
				actual=actual.getDir();
			}
			actual.setDir(new Nodo(dato));
		}
	}
	
	public T quitaPrimerDato() {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else {
			T resp=primero.getDato();
			Nodo<T>actual=primero;
			primero=primero.getDir();
			actual.setDato(null);
			return resp;
		}
	}
	
	public T quitaUltimoDato() {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		T resp;
		if(primero.getDir()==null){
			resp=quitaPrimerDato();
		}
		else {
			Nodo<T>anterior=null;
			Nodo<T>actual=primero;
			while(actual.getDir()!=null) {
				anterior=actual;
				actual=actual.getDir();
			}
			resp=actual.getDato();
			anterior.setDir(null);
		}
		return resp;
	}
	
	public T quitaDato(T dato) {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		T resp=null;
		if(primero.getDir()==null && primero.getDato().equals(dato)) {
			resp=quitaPrimerDato();
		}
		else if(!primero.getDato().equals(dato)) {
			throw new RuntimeException("No esta el dato");
		}
		else {
			Nodo<T>actual=primero;
			Nodo<T>anterior=null;
			while(actual.getDato().equals(dato)) {
				anterior=actual;
				actual=actual.getDir();
			}
			resp=actual.getDato();
			anterior.setDir(actual.getDir());
			actual.setDir(null);
		}
			return resp;	
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder();
		return toString(sb,primero);
	}
	
	private String toString(StringBuilder sb, Nodo <T> actual) {
		if(actual==null) {
			return sb.toString();
		}
		else {
			sb.append(actual.getDato());
			return toString(sb,actual.getDir());
		}
	}
	
	public boolean contiene(T dato) {
		boolean resp;
		Nodo<T>actual=primero;
		return contiene(dato,actual);
	}
	
	private boolean contiene(T dato, Nodo<T>actual) {
		if(actual==null) {
			return false;
		}
		else {
			if(dato.equals(actual.getDato())) {
				return true;
			}
			else {
				return contiene(dato,actual.getDir());
			}
		}
	}
	
	public boolean eliminaSiguienteDe(T dato) {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		boolean resp=false;
		if(primero.getDir()==null) {
			throw new RuntimeException("Solo contiene 1 dato"); 
		}
		else {
			Nodo<T>actual=primero;
			Nodo<T>despues=null;
			while(!actual.getDato().equals(dato) && actual.getDir()!=null) {
				actual=actual.getDir();
			}
			if(actual.getDir()!=null) {		
				despues=actual.getDir();
				actual.setDir(despues.getDir());
				despues.setDir(null);
				resp=true;
			}
		}
		return resp;
	}
	
	public boolean eliminaAnteriorA(T dato) {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		boolean resp=false;
		if(primero.getDir()==null) {
			throw new RuntimeException("Solo contiene 1 dato"); 
		}
		else {
			Nodo<T>actual=primero;
			Nodo<T>antes=null;
			Nodo<T>antes2=null;
			int i=0;
			while(actual!=null && !actual.getDato().equals(dato)) {
				antes2=antes;
				antes=actual;
				actual=actual.getDir();
				i++;
			}
			if(actual!=null) {		
				if(i==1) {
					antes.setDir(null);
					primero=actual;
					resp=true;
				}
				else if(i==0) {
					resp=false;
				}
				else {
					antes2.setDir(actual);
					antes.setDir(null);
					resp=true;
				}
			}
		}
		return resp;
	}
	
	public Iterator iterator() {
		return new IteradorEE(primero);
	}
	
	public int cuentaNodos() {
		IteradorEE a=new IteradorEE(primero);
		int i=0;
		return cuentaNodos(a,i);
	}
	private int cuentaNodos(IteradorEE a,int i) {
		if(!a.hasNext()) {
			return i;
		}
		else {
			a.next();
			return cuentaNodos(a,i+1);
		}
	}
	
	public boolean insertaAntesQue(T ref,T dato) {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		boolean resp=false;
		if(primero.getDato().equals(ref)) {
			agregaInicio(dato);
			resp=true;
		}
		else {
			Nodo<T>actual=primero;
			Nodo<T>anterior=null;
			Nodo<T>nuevo=null;

			while(actual!=null && !actual.getDato().equals(ref)) {
				anterior=actual;
				actual=actual.getDir();
			}
			if(actual!=null) {
				nuevo=new Nodo(dato);
				anterior.setDir(nuevo);
				nuevo.setDir(actual);
				resp=true;
			}
		}
		return resp;
	}
	
	public int eliminaTodosRepOrden() {
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else if(primero.getDir()==null) {
			throw new RuntimeException("Un solo elemento");
		}
		int i=0;
		Nodo<T>actual=primero,siguiente=null,antesSig=null;
		T dato=primero.getDato();
		while(actual!=null) {
			siguiente=actual.getDir();
			dato=actual.getDato();
			if(siguiente!=null&&siguiente.getDato().equals(dato)) {
				while(siguiente.getDato().equals(dato)&&siguiente.getDir()!=null) {
					antesSig=siguiente;
					siguiente=siguiente.getDir();
					antesSig.setDir(null);
					i++;
				}
				if(siguiente.getDato().equals(dato)&&siguiente.getDir()==null) {
					actual.setDir(null);
					i++;
				}
				else {
				actual.setDir(siguiente);
				actual=siguiente;
				dato=siguiente.getDato();
				}
			}
			else {
				actual=siguiente;
				if(siguiente!=null) {
					dato=siguiente.getDato();
				}
			}
		}
		return i;
	}
	
	public int eliminaTodosRepDesorden(){//es ineficiente
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else if(primero.getDir()==null) {
			throw new RuntimeException("Coleccion con un dato");
		}
		Nodo<T>actual=primero,anterior=null;
		ArrayList<T>list=new ArrayList();
		int i=0;
		T dato=actual.getDato();
		while(actual.getDir()!=null) {
			if(!list.contains(dato)) {
				list.add(dato);
				anterior=actual;
			}
			else {
				anterior.setDir(actual.getDir());
				i++;
				actual=anterior;
			}
			actual=actual.getDir();
			dato=actual.getDato();
		}
		if(actual.getDato().equals(dato)) {
			anterior.setDir(null);
			i++;
		}
		return i;
	}

	public int eliminaTodosRepDesorden2(){
		if(estaVacia()) {
			throw new RuntimeException("Coleccion vacia");
		}
		else if(primero.getDir()==null) {
			throw new RuntimeException("Coleccion con un dato");
		}
		Nodo<T>actual=primero.getDir(),anterior=primero,base=primero.getDir(),base2=primero;
		T dato=base2.getDato();
		int i=0;
		while(actual!=null) {
			while(actual.getDir()!=null) {
				if(actual.getDato().equals(dato)) {
					anterior.setDir(actual.getDir());
					actual.setDir(null);
					actual=anterior.getDir();
					i++;
				}
				else {
					anterior=actual;
					actual=actual.getDir();
				}
			}
			if(dato.equals(actual.getDato())) {
				anterior.setDir(null);
				i++;
			}
			base=base.getDir();
			actual=base;
			try {
			base2=base2.getDir();
			dato=base2.getDato();
			}
			catch(Exception a){
				//
			}	
		}
		return i;
	}
	
	public void intercalaNodos(EE node) {
		if(estaVacia()) {
			this.primero=node.getPrimero();
		}
		else if(node.estaVacia()) {
			//
		}
		else {
			Nodo<T>actual=primero;
			Nodo<T>aux=actual.getDir();
			Nodo<T>actualN=node.getPrimero();
			Nodo<T>auxN=actualN.getDir();
			
			while(aux!=null && auxN!=null) {
				actual.setDir(actualN);
				actualN.setDir(aux);
				actual=aux;
				aux=aux.getDir();
				actualN=auxN;
				auxN=auxN.getDir();
			}
			if(aux==null) {
				actual.setDir(actualN);
			}
			else if(auxN==null){
				actual.setDir(actualN);
				actualN.setDir(aux);
			}
		}
	}
	
	public static void main(String[]args) {
		
		EE a=new EE();
		a.agregaFinal(1);
		a.agregaFinal(2);
		a.agregaFinal(3);
		a.agregaFinal(4);
		a.agregaFinal(5);
		
		System.out.println(a.toString());
		a.eliminaAnteriorA(4);
		System.out.println(a.toString());
		a.eliminaSiguienteDe(4);
		System.out.println(a.toString());
		
		EE b=new EE();//coleccion vacia, tira el catch
		try {
			b.eliminaAnteriorA(2);
		}catch(Exception e) {
			System.out.println("\n"+"Coleccion vacia");
		}
		
		EE c=new EE(); //coleccion con 2 datos
		c.agregaFinal(1);
		c.agregaFinal(2);
		System.out.println("\n"+c.toString());
		System.out.println(c.eliminaAnteriorA(1));//si encuentra al dato en el primer nodo
		c.eliminaAnteriorA(2);
		System.out.println(c.toString());
		
		EE d=new EE();
		d.agregaFinal(1);
		d.agregaFinal(1);
		d.agregaFinal(1);
		d.agregaFinal(1);
		d.agregaFinal(1);
		d.agregaFinal(1);
		d.agregaFinal(1);
		
		System.out.println(d.toString());
		System.out.println(d.eliminaTodosRepOrden());
		System.out.println(d.toString());
		
		EE e=new EE();
		e.agregaFinal(1);
		e.agregaFinal(1);
		e.agregaFinal(2);
		e.agregaFinal(3);
		e.agregaFinal(3);
		e.agregaFinal(4);
		e.agregaFinal(4);


		System.out.println("\n"+e.toString());
		System.out.println(e.eliminaTodosRepOrden());
		System.out.println(e.toString());

		
		EE f=new EE();
		f.agregaFinal(1);
		f.agregaFinal(2);
		f.agregaFinal(3);
		f.agregaFinal(1);
		f.agregaFinal(2);
		f.agregaFinal(3);
		System.out.println("\n"+f.toString());
		System.out.println(f.eliminaTodosRepDesorden());
		System.out.println(f.toString());

		EE g=new EE();
		g.agregaFinal(1);
		g.agregaFinal(1);
		g.agregaFinal(1);
		g.agregaFinal(1);
		g.agregaFinal(1);
		g.agregaFinal(1);
		System.out.println("\n"+g.toString());
		System.out.println(g.eliminaTodosRepDesorden());
		System.out.println(g.toString());
		
		EE h=new EE();
		h.agregaFinal(1);
		h.agregaFinal(3);
		h.agregaFinal(4);
		h.agregaFinal(3);
		h.agregaFinal(5);
		h.agregaFinal(1);
		System.out.println("\n"+h.toString());
		System.out.println(h.eliminaTodosRepDesorden());
		System.out.println(h.toString());
		
		EE i=new EE();
		i.agregaFinal(1);
		i.agregaFinal(2);
		i.agregaFinal(3);
		i.agregaFinal(1);
		i.agregaFinal(2);
		i.agregaFinal(3);
		System.out.println("\nSegunda: "+i.toString());
		System.out.println(i.eliminaTodosRepDesorden2());
		System.out.println(i.toString());

		EE j=new EE();
		j.agregaFinal(1);
		j.agregaFinal(1);
		j.agregaFinal(1);
		j.agregaFinal(1);
		j.agregaFinal(1);
		j.agregaFinal(1);
		System.out.println("\n"+j.toString());
		System.out.println(j.eliminaTodosRepDesorden2());
		System.out.println(j.toString());
		
		EE k=new EE();
		k.agregaFinal(1);
		k.agregaFinal(3);
		k.agregaFinal(4);
		k.agregaFinal(3);
		k.agregaFinal(5);
		k.agregaFinal(1);
		System.out.println("\n"+k.toString());
		System.out.println(k.eliminaTodosRepDesorden2());
		System.out.println(k.toString());
		
		EE l =new EE();
		l.agregaFinal(5);
		l.agregaFinal(9);
		l.agregaFinal(5);
		l.agregaFinal(5);
		l.agregaFinal(5);
		l.agregaFinal(5);
		System.out.println("\n"+l.toString());
		System.out.println(l.eliminaTodosRepDesorden2());
		System.out.println(l.toString());
		
		System.out.println(l.toString());
		l.intercalaNodos(k);
		System.out.println(l.toString());
	}
}
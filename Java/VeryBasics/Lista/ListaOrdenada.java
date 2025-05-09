package Lista;
/*
 * Gerardo Moguel
 */
public class ListaOrdenada <T extends Comparable<T>> extends Lista<T> implements ListaOrdenadaADT<T>{
	
	public boolean equals(ListaOrdenada otro) {//revisar
		boolean resp=false;
		if(primero.getDato().equals(otro.getPrimerDato()) && ultimo.getDato().equals(otro.getUltimoDato())) {
			NodoDoble<T>auxA=primero.getSig();
			NodoDoble<T>auxB=otro.primero.getSig();
			while(auxA.equals(auxB)&&auxA.getSig()!=null) {
				auxA=auxA.getSig();
				auxB=auxB.getSig();
			}
			if(auxA.equals(auxB)) {
				resp=true;
			}
		}
		return resp;
	}
	
    public boolean agrega(T dato) {
    	NodoDoble<T> nuevo=new NodoDoble(dato);
    	boolean resp=false;
    	if(estaVacia()) {
    		primero=nuevo;
    		ultimo=primero;
    		resp=true;
    	}
    	else {
    		if(primero.getDato().compareTo(dato)>=0) {
    			if(!primero.getDato().equals(dato)) {
    				nuevo.setSig(primero);
    				primero.setAnt(nuevo);
    				primero=nuevo;
    				resp=true;
    			}
    		}
    		else if(ultimo.getDato().compareTo(dato)<=0){
    			if(!ultimo.getDato().equals(dato)) {
    				ultimo.setSig(nuevo);
    				nuevo.setAnt(ultimo);
    				ultimo=nuevo;
    				resp=true;
    			}
    		}
    		else {
    			NodoDoble<T> actual=primero.getSig();
    			while(dato.compareTo(actual.getDato())>0) {
    				actual=actual.getSig();
    			}
    			if(!dato.equals(actual.getDato())) {
	    			nuevo.setSig(actual);
	    			nuevo.setAnt(actual.getAnt());
	    			actual.getAnt().setSig(nuevo);
	    			actual.setAnt(nuevo);
	    			resp=true;
    			}
    		}
    	}
    	return resp;
    }

	public boolean quita(T dato) {
		if(primero.getDato().equals(dato)){
			if(primero.getSig()!=null) {
				primero=primero.getSig();
				primero.getAnt().setSig(null);
				primero.setAnt(null);
				return true;
			}
			else {
				primero=null;
				ultimo=null;
				return true;
			}
		}
		else if(ultimo.getDato().equals(dato)) {
			ultimo=ultimo.getAnt();
			ultimo.getSig().setAnt(null);
			ultimo.setSig(null);
			return true;
		}
		else {
			NodoDoble<T>actual=primero.getSig();
			return quita(dato,actual);
		}
	}
	private boolean quita(T dato, NodoDoble<T>actual) {
		if(actual==null) {
			return false;
		}
		else if(actual.equals(dato)) {
			actual.getAnt().setSig(actual.getSig());
			actual.getSig().setAnt(actual.getAnt());
			actual.setAnt(null);
			actual.setSig(null);
			return true;
		}
		else {
			return quita(dato, actual.getSig());
		}
	}
	
	public boolean contiene(T dato) {
		if(primero.getDato().equals(dato)){
			return true;
		}
		else if(ultimo.getDato().equals(dato)) {
			return true;
		}
		else {
			NodoDoble<T>actual=primero.getSig();
			return contiene(dato,actual);
		}
	}
	private boolean contiene(T dato, NodoDoble<T>actual) {
		if(actual==null) {
			return false;
		}
		else if(actual.equals(dato)) {
			return true;
		}
		else {
			return quita(dato, actual.getSig());
		}
	}
	
	public boolean estaContenida(Object obj) {
		boolean resp=true;
		if(obj==null && !(obj instanceof ListaOrdenada)) {
			resp=false;
		}
		else {
			ListaOrdenada<T>lista=(ListaOrdenada<T>) obj;
			if((estaVacia()&&!lista.estaVacia())||(!estaVacia()&&lista.estaVacia())) {
				resp=false;
			}
			else if(estaVacia()&&lista.estaVacia()){
				resp=true;
			}
			else if(ultimo.getDato().compareTo(lista.ultimo.getDato())<0) {
				resp=false;
			}
			else {
				NodoDoble<T>aux=primero;
				NodoDoble<T>aux2=lista.primero;
				while(aux2!=null && aux!=null && resp) {
					while(!aux.equals(aux2)&&aux.getDato().compareTo(aux2.getDato())<0) {
						aux=aux.getSig();
					}
					if(aux.getDato().equals(aux2.getDato())) {
						aux=aux.getSig();
						aux2=aux2.getSig();
					}
					else {
						resp=false;
					}
				}
				
			}
		}
		return resp;
	}

	private boolean estaContenidaRec(NodoDoble<T>aux,NodoDoble<T>aux2, boolean resp) {
		if(!aux.equals(aux2)&&aux.getDato().compareTo(aux2.getDato())<0) {
			aux=aux.getSig();
			return estaContenidaRec(aux,aux2,resp);
		}
		else if(aux.getDato().equals(aux2.getDato())){
			aux=aux.getSig();
			aux2=aux2.getSig();
			return estaContenidaRec(aux,aux2,resp);
		}
		else if(aux.getDato().compareTo(aux2.getDato())>0){
			return false;
		}
		else{
			return resp;
		}
	}
	
    public static void main(String[]args) {
    	ListaOrdenada a=new ListaOrdenada();
    	ListaOrdenada b=new ListaOrdenada();
    	ListaOrdenada c=new ListaOrdenada();

    	
    	a.agrega(1);
    	a.agrega(2);
    	a.agrega(3);
    	a.agrega(6);
    	
    	b.agrega(1);
    	b.agrega(2);
    	b.agrega(3);
    	b.agrega(4);
    	
    	c.agrega(1);
    	c.agrega(2);
    	c.agrega(3);



    	
    	System.out.println(b.contiene(2));

    	System.out.println(a.toString());
    	System.out.println(b.toString());
    	System.out.println(a.equals(b));
    	System.out.println(a.estaContenida(b));
    	System.out.println(a.estaContenida(c));

    }
}

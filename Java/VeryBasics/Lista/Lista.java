package Lista;
import java.util.Iterator;

public abstract class Lista<T> implements ListaADT<T> {
    protected NodoDoble<T> primero;
    protected NodoDoble<T> ultimo;
    
    protected Lista(){ //casos Vacia, solo uno , dos o mas 
        this.primero=null;
        this.ultimo=null;    
    }

    public Iterator<T> iterator() {
        return new IteradorLista(primero);
    }
   
    public boolean estaVacia() {
        return primero==null;
    }
    
    public T getPrimerDato() {
    	T resp=null;
    	if(primero!=null) {
    		resp=primero.getDato();
    	}
    	return resp;
    }
    
    public T getUltimoDato() {
    	T resp=null;
    	if(ultimo!=null) {
    		resp=ultimo.getDato();
    	}
    	return resp;
    }
    
    public T quitaPrimero() {
        T resp= null;
        if(!estaVacia()){
            resp=primero.getDato();
            primero=primero.getSig();
            if(primero==null) {
                ultimo=null;
            }
            else{
                primero.getAnt().setSig(null);
                primero.setAnt(null);
            }
        }
        return resp;
    }

    public T quitaUltimo() {
        T resultado=null;
        if(!estaVacia()){
           resultado=ultimo.getDato();
           ultimo=ultimo.getAnt();
           if(ultimo==null) {
                primero=null;
           }
           else{
               ultimo.getSig().setAnt(null);
               ultimo.setSig(null);
           }
        }
            return resultado;
    }

    public T quitaDato(T dato) {
        T resultado= null;
        if(!estaVacia()){
            if(dato.equals(primero.getDato())) {
                resultado=quitaPrimero();
            }
            else {
                if(dato.equals(ultimo.getDato())) {
                    resultado=quitaUltimo();
                }
                else{
                    NodoDoble<T> actual = primero.getSig();
                    while(actual!=null && !actual.getDato().equals(dato)) {
                        actual=actual.getSig();
                    }
                    if(actual != null){//El dato esta en posición intermedia 
                        resultado = actual.getDato();
                        actual.getAnt().setSig(actual.getSig());
                        actual.getSig().setAnt(actual.getAnt());
                        actual.setAnt(null);
                        actual.setSig(null);
                    }
                }
            }
        }
        return resultado;
    }

    public boolean contiene(T dato) {
        boolean resp;
        NodoDoble<T> izq, der;
        resp=false;
        izq = primero;
        der= ultimo;
        
        while(izq != der  && !izq.getDato().equals(dato) && !der.getDato().equals(dato)){
            izq = izq.getSig();
            der= der.getAnt();
        }
        if(izq != null)
            resp =izq.getDato().equals(dato) || der.getDato().equals(dato);
        
        return resp;
               
    }

    public int calculaTamanio() {
        int contador;
        NodoDoble<T> actual;
        contador=0;
        actual = primero;
        while(actual != null){
            contador ++;
            actual = actual.getSig();
        }
        return contador;
        
    }

    public T consultaPrimero() {
        
        if(estaVacia())
            throw new RuntimeException();
        
        return primero.getDato();
         
    }

    public T consultaUltimo() {    
        if(estaVacia())
            throw new RuntimeException();
        
        return ultimo.getDato();
         
    }
    
    public String toString(){
        StringBuilder sB = new StringBuilder();
        NodoDoble<T> actual;
        actual= primero;
        while(actual != null){
            sB.append(actual.getDato());
            actual = actual.getSig();
        }
        return sB.toString();
    }
}
    
    
    
    



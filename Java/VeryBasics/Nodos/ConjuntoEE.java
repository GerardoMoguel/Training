package Nodos;

import java.util.Iterator;

/**
 *
 * Gerardo Moguel
 */
public class ConjuntoEE <T> implements ConjuntosADT<T>{
    private EE nodo; 
    private final int MAX=100;
    
    public ConjuntoEE(){
        nodo=new EE();
    }
    //La clase Iterator nos sirve para poder aplicar distintos metodos dentro del conjunto
    public Iterator<T> iterator() { //Constructor de clase iterator, esto es posible pues implementa la clase ADT, que extiende
        return new IteradorEE(nodo.getPrimero());//la clase Iterator.
    }
    
    public EE<T> getNodo(){
    	return nodo;
    }
    
    public boolean estaVacio() { //Determina si el conjunto se encuentra vacio
       return nodo.estaVacia(); 
    }
     
    public boolean pertenece(T dato) { //Metodo para detectar si cierto dato T se encuentra en el conjunto de datos T
       return nodo.contiene(dato);    
     }
    
    public Nodo<T> getPrimero(){
    	return nodo.getPrimero();
    }
    public boolean agrega(T nuevo) { //Metodo para agregar un nuevo dato tipo T dentro del arreglo tipo T
        boolean respuesta=false; 
        
        if(!pertenece(nuevo)){
            respuesta= true;
            nodo.agregaFinal(nuevo);
        }
        return respuesta; 
    }
   
    public T quita(T dato) {
    	return (T) nodo.quitaDato(dato);
    }
    
    private void agregaConjunto(Iterator<T>it,ConjuntosADT<T> res) {
    	if(it.hasNext()) {
    		res.agrega(it.next());
    		agregaConjunto(it, res);
    	}
    } 
    
    public ConjuntosADT<T> union(ConjuntosADT otro) {
    	ConjuntoEE<T> res=new ConjuntoEE();
    	Nodo<T>actualA=nodo.getPrimero();
    	Nodo<T>actualB=otro.getNodo().getPrimero();
    	while(actualA!=null) {
    		res.agrega(actualA.getDato());
    		actualA=actualA.getDir();
    	}
    	while(actualB!=null) {
    		res.agrega(actualB.getDato());
    		actualB=actualB.getDir();
    	}
    	res.getNodo().eliminaTodosRepDesorden2();
    	return res;
    }
}
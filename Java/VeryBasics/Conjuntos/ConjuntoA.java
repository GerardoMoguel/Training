package Conjuntos;

import java.util.Iterator;

/**
 *
 * @author User
 */
public class ConjuntoA <T> implements ConjuntosADT<T> {
    private T [] conjunto; 
    private int cardinalidad; 
    private final int MAX=100;
    
    public ConjuntoA(){
        conjunto=(T[])new Object[MAX];
        cardinalidad= 0; 
    }
    
    public ConjuntoA(int max){
        conjunto=(T[])new Object[max];
        cardinalidad= 0; 
    }
   
    //La clase Iterator nos sirve para poder aplicar distintos metodos dentro del conjunto
    public Iterator<T> iterator() { //Constructor de clase iterator, esto es posible pues implementa la clase ADT, que extiende
        return new IteratorArre(conjunto, cardinalidad);//la clase Iterator.
    }
    
    public boolean estaVacio() { //Determina si el conjunto se encuentra vacio
        return cardinalidad==0; 
    }
    
    public int getCardinalidad() { //Obtenemos el valor "Cardinalidad".
    	return cardinalidad;
    }
    
    public boolean pertenece(T dato) { //Metodo para detectar si cierto dato T se encuentra en el conjunto de datos T
        int i=0; 
        while(i<cardinalidad &&!conjunto[i].equals(dato)) {
     	   i++;
        }
 	   return i<cardinalidad;       
     }
    
    public boolean agrega(T nuevo) { //Metodo para agregar un nuevo dato tipo T dentro del arreglo tipo T
        boolean respuesta=false; 
        
        if(!pertenece(nuevo)){
            respuesta= true;
            if(cardinalidad==conjunto.length) {
                expande();
            }
            conjunto[cardinalidad]=nuevo; 
            cardinalidad++;
        }
        return respuesta; 
    }
    
    //metodo con funcion de expandir el arreglo "conjunto", este le suma al length del array original 50 unidades.
    public void expande(){
        T[]x= (T[])new Object[conjunto.length+50];
        for(int i=0; i<cardinalidad; i++){
            x[i]=conjunto[i];
        }
        conjunto=x;
    }

    //Metodo que encuentra la posicion del dato T, y regresa el indice en el que se encuentra.
    private int encuentraPosicion(T dato){
        int i=0; 
        while(i<cardinalidad && conjunto[i].equals(dato)) {
        	i++;
        }
        if(i==cardinalidad) {
            i=-1;
        }
        return i; 
    }
   
    public T quita(T dato) {
    	int i=encuentraPosicion(dato);
    	T resp=null;
    	if(i!=-1) {
    		resp=conjunto[i];
    		conjunto[i]=conjunto[cardinalidad-1];
    		conjunto[cardinalidad-1]=null;
    		cardinalidad--;
    	}
    	return resp;
    }
    
    public void copiaConjunto(ConjuntosADT<T> origen,ConjuntoA<T> destino) {
    	Iterator<T> it=origen.iterator();
    	int i=0;
    	while(it.hasNext()) {
    		destino.conjunto[i]=it.next();
    		i++;
    	}
    	destino.cardinalidad=i;
    }
    
    private void agregaConjunto(Iterator<T>it,ConjuntosADT<T> res) {
    	if(it.hasNext()) {
    		res.agrega(it.next());
    		agregaConjunto(it, res);
    	}
    } 
    
    public ConjuntosADT<T> interseccion(ConjuntosADT<T> otro) {
    	ConjuntoA<T> res=new ConjuntoA();
    	
    	if(getCardinalidad()>otro.getCardinalidad()) {
    		copiaConjunto(this,res);
    		agregaConjunto(this.iterator(),res);
    	}
    	else {
    		copiaConjunto(otro,res);
    		agregaConjunto(this.iterator(),res);
    	}
    	return res;
    }
    
    public ConjuntosADT<T> union(ConjuntosADT<T> otro) {
    	ConjuntoA<T> aux=new ConjuntoA();
    	ConjuntoA<T> res=new ConjuntoA();

 
    	if(getCardinalidad()>otro.getCardinalidad()) {
    		copiaConjunto(this,aux);
        	Iterator<T> it=this.iterator();
        	while(it.hasNext()) {
        		T var=it.next();
        		if(otro.pertenece(var)) {
        			res.agrega(var);
        		}
        	}
    	}
    	else {
    		copiaConjunto(otro,aux);
        	Iterator<T> it=otro.iterator();
        	while(it.hasNext()) {
        		T var=it.next();
        		if(this.pertenece(var)) {
        			res.agrega(var);
        		}
        	}
    	}
    	return res;
    }
    
    public String toString() {
    	StringBuilder sb=new StringBuilder();
    	for(int i=0;i<cardinalidad;i++) {
    		sb.append(conjunto[i]+" ");
    	}
    	return sb.toString();
    }
     
   public static void main (String []args) {
	   ConjuntoA uno=new ConjuntoA();
	   ConjuntoA dos=new ConjuntoA();

	   uno.agrega(1);
	   uno.agrega(2);
	   uno.agrega(3);
	   uno.agrega(4);
	   uno.agrega(5);
	   
	   dos.agrega(4);
	   dos.agrega(5);
	   dos.agrega(6);

	  System.out.println(uno.interseccion(dos).toString());

   }
}

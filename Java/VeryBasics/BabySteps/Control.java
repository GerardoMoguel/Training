
import java.util.ArrayList;

public class Control {

    public Control() {

    }

    public <T> ArrayList<T> regresarRepetidos(T[] arreglo, int total) {
        ArrayList respuesta = new ArrayList(); 
        int i;
        
        for (i = 0; i<total; i++) {
        	T dato = arreglo[i]; // nos da el dato que esta en la primer casilla del arreglo
        	for(int j = i+1 ; j<total; j++) {
        		if(dato.equals(arreglo[j])) { //para poder usar este metodo, de ahuevo la clase donde lo uses necesita tener un equals
        			if(!respuesta.contains(dato)) {
        				respuesta.add(dato);
        			}
        		}
        	}
        }
        return respuesta;
    }
    
    public static void main (String[]args) {
    	Control a = new Control();
    	String [] nombres = {"Pato","Pato","Rodrick","Dios","Baltazar","Baltazar"};
    	
		System.out.println(a.regresarRepetidos(nombres, 6));
		System.out.println(a.regresarRepetidos(nombres, 6).size());
		
    }
    
}
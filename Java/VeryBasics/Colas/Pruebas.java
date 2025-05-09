package Colas;

/**
 *
 * @author User
 */
public class Pruebas <T> {
    //Ejercicio 36
    public static <T> void quitaRepe (ColaA col){
        ColaA aux=new ColaA(); 
        try {
        aux.agrega(col.quita());
        T elemento=null;
        
        while(!col.estaVacia()){
            elemento= (T)col.quita();
            if(!aux.ultimo().equals(elemento)){
                aux.agrega(elemento);
            }
        }
       while(!aux.estaVacia()) {
    	   col.agrega(aux.quita());
       }
        }catch (Exception e){
        	throw new RuntimeException("Cola vacia");
        }
    }

    public static void main(String[]args) {
    	 ColaA co= new ColaA ();
         
         co.agrega(1);
         co.agrega(2);
         co.agrega(2);         
         co.agrega(3);
         co.agrega(3);
         co.agrega(4);
         co.agrega(4);
         co.agrega(2);

         quitaRepe(co);
        System.out.println(co.getCola()[0]);
        System.out.println(co.getCola()[1]);
        System.out.println(co.getCola()[2]);
        System.out.println(co.getCola()[3]);
        System.out.println(co.getCola()[4]);
        System.out.println(co.getCola()[5]);


    }
}

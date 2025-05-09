/*
 * Gerardo Moguel
 * Prueba de clase cientifico matematico
 */
public class PruebaCM {

	public static void main(String[] args) {
CM objetoCM= new CM ("Pedro","Calculo");

System.out.println("\nOrdena numeros: "+ objetoCM.ordenaCreciente(18, 12));

if (objetoCM.ordenCreciente(15, 24))
	System.out.println("\nSi esta en orden...");
else 
	System.out.println("\nNo hay orden...");

System.out.println("\nResultados de la funcion 2: "+objetoCM.calculaFuncion2(7));

if (objetoCM.esImpar(5))
	System.out.println("\nTrue, es impar...");
else 
	System.out.println("\nFalse, es par...");

System.out.println("\nResultado de la funcion 1: "+objetoCM.calculaFuncion1(23));

System.out.println("\nResultado de la funcion 3: "+objetoCM.calculaFuncion3(2,2));

	}//main
}//class

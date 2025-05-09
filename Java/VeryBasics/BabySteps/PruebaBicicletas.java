/*
 * Gerardo Moguel
 * Clase de prueba para clase bicicletas
 */
public class PruebaBicicletas {
	public static void main(String[] args) {

Bicicletas bici1=new Bicicletas('P',1,'F');
Bicicletas bici2=new Bicicletas('M',14,'A');
System.out.println(bici1.calcularCosto());
System.out.println(bici1.getId());

System.out.println("\n"+bici2.calcularCosto());
System.out.println(bici2.getId());

System.out.println(bici2.compararBicis(200));
	}//main
}//class

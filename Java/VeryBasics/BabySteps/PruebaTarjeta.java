/*
 * Gerardo Moguel
 * Clase de prueba de clase tarjeta
 */
public class PruebaTarjeta {

	public static void main(String[] args) {
Tarjeta uno=new Tarjeta(1234,'T',2,2022);
Tarjeta dos= new Tarjeta(4321,'A',1,2022);

dos.calculaCredito();
dos.calculaComision();
System.out.println("\nEl limite de credito es de: $"+dos.getLimCred()+" MXN.");
System.out.println("La comision de: $"+dos.getComision()+" MXN.");


	}//main
}//class

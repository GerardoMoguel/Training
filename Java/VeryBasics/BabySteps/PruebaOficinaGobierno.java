/*
 * Gerardo M
 * Prueba de oficina gobierno
 */
public class PruebaOficinaGobierno {

	public static void main(String[] args) {
OficinaGobierno pepe=new OficinaGobierno();
pepe.AltaDatos("Chiapas", 10);
pepe.AltaDatos("CDMX", 20);
pepe.AltaDatos("Sonora", 400);
pepe.AltaDatos("Tampico", 10);
pepe.AltaDatos("Tamaulipas", 20);
System.out.println(pepe.AltaDatos("Sinaloa", 40)); 
pepe.AltaDatos("Monterrey", 20);
pepe.AltaDatos("Tabasco", 10);

//System.out.println(pepe.EstadoMPoblado());
System.out.println(pepe.promedioNacional());
System.out.println(pepe.menorProm());
	}
//funciona muy bien :>
}

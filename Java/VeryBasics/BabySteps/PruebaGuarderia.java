/*
 * Clase de prueba de guarderia
 * Gerardo M
 */
public class PruebaGuarderia {
	public static void main(String[] args) {

Guarderia elPepe=new Guarderia ("El Pepe");
elPepe.altaEdad(0);
elPepe.altaEdad(1);
elPepe.altaEdad(2);
elPepe.altaEdad(1);
elPepe.altaEdad(2);
elPepe.altaEdad(0);
elPepe.altaEdad(0);

System.out.println(elPepe.calculaConsumoP());
System.out.println(elPepe.promDiarioConsumoP());
	}
}

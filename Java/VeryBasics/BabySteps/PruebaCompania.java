
public class PruebaCompania {

	public static void main(String[] args) {
Compania uno=new Compania("Gas pepe","Av. Sinaloa 2","CDMX","Sra. Julisa");
uno.altaDatos(123);
uno.altaDatos(125);
uno.altaDatos(123);
uno.altaDatos(122);
uno.altaDatos(123);
uno.altaDatos(124);
uno.altaDatos(123);
uno.altaDatos(124);
uno.altaDatos(123);
uno.altaDatos(123);
uno.altaDatos(123);
System.out.println(uno.altaDatos(123));
System.out.println(uno.altaDatos(12340));

System.out.println("Promedio de ventas: "+uno.promVentas());
System.out.println("Mes con mas ventas fue: "+uno.mesMasV());
System.out.println("Mes con menos ventas fue: "+uno.mesMenosV());
System.out.println("Cantidad de meses con ventas mayores al promedio: "+uno.masProm());
System.out.println("Total de ventas al anio: "+uno.totalVAnio());

	}

}

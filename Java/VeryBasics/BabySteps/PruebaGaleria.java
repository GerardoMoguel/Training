/*
 * Gerardo M
 * Prueba de la clase galeria
 */
public class PruebaGaleria {

	public static void main(String[] args) {
Galeria elPepe=new Galeria("El pepe","Las meninas",'O',10,2000,500);
Galeria elsofi=new Galeria("lele","PONS",'A',100000,200,500);

//elPepe.darDescuento("El pepe", 30);// las dos sirven bien
//elPepe.calcularPrecioV();
elsofi.calcularPrecioV();
elPepe.calculaPrecioF("El pepe",30);
System.out.println(elPepe.getPrecio());//hablo de esta


//elsofi.clavesIguales(1002);
	}

}

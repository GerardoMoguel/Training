import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prueba {

	public static void main(String[] args) {
		
		//creacion de la clase Stock, agregamos el nombre del ingrediente, su origen y el precio por 1 kilo/litro,
		//despues el estado de la materia en que se encuentra 0=solido 1=liquido 2=gaseoso
		Stock s1=new Stock("uno");
		
		//todos los precios son por 1 kilo o litro
		s1.altaIngrediente("arroz", "U.S.A", 84, 0);
		s1.altaIngrediente("alga nori", "Japon", 5, 0);//en el caso del alga nori, es por unidad.
		s1.altaIngrediente("vinagre de arroz", "Mexico", 35, 1);
		s1.altaIngrediente("wasabi", "Japon", 5000, 0);
		s1.altaIngrediente("salsa de soya", "Mexico", 45, 1);
		s1.altaIngrediente("gengibre", "Mexico", 50, 0);
		s1.altaIngrediente("aguacate", "Mexico", 76, 0);
		s1.altaIngrediente("mayo spicy", "Mexico", 230, 1);
		s1.altaIngrediente("akami", "Mexico, Ensenada Baja California", 2000, 0);
		s1.altaIngrediente("chutoro", "Mexico, Ensenada Baja California", 3000, 0);
		s1.altaIngrediente("otoro", "Mexico, Ensenada Baja California", 3500, 0);
		s1.altaIngrediente("jaiba cs","Mexico, Ensenada Baja California", 950, 0);//cs= concha suave
		s1.altaIngrediente("salmon", "Canada", 700, 0);
		s1.altaIngrediente("tobiko", "Paris", 1400, 0);
		s1.altaIngrediente("ikura", "Mexico, Ensenada Baja California", 2950, 0);
		s1.altaIngrediente("hamachi", "Mexico, Ensenada Baja California", 1400, 0);
		s1.altaIngrediente("anguila", "Mexico", 1200, 0);
		s1.altaIngrediente("cebollin", "Mexico", 140, 0);
		s1.altaIngrediente("lobina", "Mexico", 700, 0);
		s1.altaIngrediente("mayo yuzu", "Mexico", 230, 1);

		//aumetamos el stock de todos los productos, representando que se tiene disponibilidad del mismo
		s1.aumentaStock("arroz", 0, 20);//representa el ingrediente "arroz", el estado solido y 20 kilos de producto
		s1.aumentaStock("alga nori", 0, 200);
		s1.aumentaStock("vinagre de arroz", 1, 10);
		s1.aumentaStock("wasabi", 0, 1);
		s1.aumentaStock("salsa de soya", 1, 5);
		s1.aumentaStock("gengibre", 0, 1);
		s1.aumentaStock("aguacate", 0, 3);
		s1.aumentaStock("mayo spicy", 1, 2);
		s1.aumentaStock("akami", 0, 1.5);
		s1.aumentaStock("chutoro", 0, 1);
		s1.aumentaStock("otoro", 0, 0.2);
		s1.aumentaStock("jaiba cs", 0, 2);
		s1.aumentaStock("salmon", 0, 2);
		s1.aumentaStock("tobiko", 0, 0.5);
		s1.aumentaStock("ikura", 0, 0.2);
		s1.aumentaStock("hamachi", 0, 0.2);
		s1.aumentaStock("anguila", 0, 1);
		s1.aumentaStock("cebollin", 0, 0.2);
		s1.aumentaStock("lobina", 0, 1.6);
		s1.aumentaStock("mayo yuzu", 1, 0.4);
		
		//creacion de la clase restaurante, en la que se crean los platillos y se les asignan ingredientes
		Restaurante uno=new Restaurante("Yoru","Roma nte");
		uno.altaPlatillo("Handroll anguila", 145);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("anguila", 0.15, 0);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("mayo yuzu", 0.05, 1);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("alga nori", 1, 0);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("tobiko", 0.02, 0);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("aguacate", 0.05, 0);
		uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].altaIngrediente("cebollin", 0.015, 0);

		//toString del platillo para ver que en efecto el platillo fue creado y tiene las cualidades proporcionadas
		System.out.println(uno.platillos[0].toString());
		
		//venta de un platillo. Se puede observar que  queda menos de 200 gr de cebollin, entre otros.
		System.out.println(uno.venta("Handroll anguila", 1,s1.getMatrizC(),s1.getMatrizI()));
		//tras vender 7 platillos, se muestra el mensaje "No hay mas mayo yuzu", invitando al usuario a ordenar mas producto.
		//la anguila se encuentra en numeros negativos, por lo que se muestra el mensaje "stock negativo" invitando al usuario a actualizarlo.
		System.out.println(uno.venta("Handroll anguila", 7,s1.getMatrizC(),s1.getMatrizI()));
		
		
		//pruebas de funciones y metodos en distintas clases
			//Clase Platillo
				//cambiar cantidad de ingrediente usado en platillo
					System.out.println("Antes:\n"+ uno.platillos[0].toString());
					uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].cambiarCantidad("anguila", 0.1);
					System.out.println("\nDespues:\n"+uno.platillos[0].toString()); //se puede observar como ha cambiado la cantidad de anguila utilizada

				//eliminar ingrediente
					System.out.println("\nAntes:\n"+ uno.platillos[0].toString());
					uno.platillos[MA.busquedaBinaria(uno.platillos, uno.getTotalP(), "Handroll anguila")].eliminaIngrediente("tobiko");
					System.out.println("\nDespues:\n"+uno.platillos[0].toString()); //se puede observar como ya no se utiliza el ingrediente tobiko
			
			//Clase Stock
				//elimina ingrediente
					System.out.println("\nAntes:\n"+MA.busquedaBinaria(s1.ingr,s1.getTotales(0),"otoro",0)); //mostrando que otoro esta en el indice 12 del array
					s1.eliminaIngrediente("otoro", 0);
					System.out.println("\nDespues:\n"+MA.busquedaBinaria(s1.ingr,s1.getTotales(0),"otoro",0)); //al haber sido eliminado nos regresa un numero negativo

				//cambiar origen y cambiar precio
					System.out.println("\nAntes:\n"+s1.buscaIngrediente("akami", 0));
					s1.cambiarOrigen("akami", 0, "Mexico, CDMX");
					s1.cambiarPrecio("akami", 0, 1750);
					System.out.println("\nDespues:\n"+s1.buscaIngrediente("akami", 0)); //el origen y el precio ha cambiado
				
				//consulta stock
					System.out.println("\nLa cantidad actual de cebollin: "+s1.consultaStock("cebollin", 0));

		//prueba de posibles errores del usuario
			//funciones o metodos con ingredientes y platillos que no existen.
				System.out.println("\n"+s1.buscaIngrediente("w", 0)); //regresa null
				System.out.println(s1.aumentaStock("a", 23, 0)); //-1 (normalmente regresa la cantidad actualizada del stock)
				System.out.println(s1.consultaStock("p", 0)); 
				System.out.println(s1.eliminaIngrediente("f", 0));//null
				System.out.println("\n"+uno.eliminaPlatillo("tiki")); //null
				System.out.println(uno.venta("e", -1, s1.getMatrizC(),s1.getMatrizI()));//regresa "false"
				System.out.println(uno.altaPlatillo("rollo jaiba cs", -240)); //false, no pueden haber precios negativos
				
		//Uso de archivo de texto
				File f=new File("Ingredientes");
				String nombre,origen;
				double precio;
				int estado;
				Ingredientes a;
				try {
			Scanner lee=new Scanner(f);
			while(lee.hasNextLine()) {
				nombre=lee.next();
				origen=lee.next();
				precio=lee.nextDouble();
				estado=lee.nextInt();
				a=new Ingredientes(nombre,origen,precio,estado);
				System.out.println("\n"+a.toString());
			}
				}catch(FileNotFoundException e) {
					System.out.println("No se pudo encontrar el archivo");
				}

	}
}

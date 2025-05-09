/*
 * Clase para determinar la cantidad de ingredientes que se tienen almacenados
 */
public class Stock {
private String nombre;
protected static Ingredientes[][]ingr=new Ingredientes[3][100];
private static int totales[];
protected static double [][]cant;
private final int MAX=100;


//constructores
public Stock(String nombre) {
	ingr=new Ingredientes[3][MAX];
	cant=new double [3][MAX];
	totales=new int [3];
	for(int i=0;i<ingr.length;i++) {
		totales[i]=0;
		for(int j=0;j<ingr[0].length;j++) {
			ingr[i][j]=new Ingredientes("a");
			cant[i][j]=0;
		}
	}	
}

public Stock(String nombre, int MAX) { //MAX redefinido.
	ingr=new Ingredientes[3][MAX];
	cant=new double [3][MAX];//3 renglones haciendo referencia a 3 posibles estados de la materia liquido, solido, gaseoso
	totales=new int [3];
	for(int i=0;i<ingr.length;i++) {
		totales[i]=0;
		for(int j=0;j<ingr[0].length;j++) {
			ingr[i][j]=null;
			cant[i][j]=0;
		}
	}
}

//gets
public static int getTotales(int indice) {
	return totales[indice];
}

public static Ingredientes[][] getMatrizI(){
	return ingr;
}
public double[][] getMatrizC(){
	return cant;
}
public String getNombre() {
	return nombre;
}

//funciones y metodos.
public boolean altaIngrediente(String nombre,String origen,double precio,int estado) {
	boolean resp=false;
	if(totales[estado]==0) {
		ingr[estado][0]=new Ingredientes(nombre,origen,precio,estado);
		resp=true;
		totales[estado]++;
	}
	if(totales[estado]<MAX && estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos<0) {
			pos=(pos+1)*-1;
			MA.recorreDer(ingr, totales[estado], pos,estado);
			ingr[estado][pos]=new Ingredientes(nombre,origen,precio,estado);
			resp=true;
			totales[estado]++;
		}
	}
	return resp;
}

public double aumentaStock(String nombre,int estado,double cantidad) {
	double resp=-1; //El -1 representa que no existe el ingrediente que se desea aumentar
	if(estado>=0 && estado<4) {
	int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos>=0) {
			cant[estado][pos]+=cantidad;
			resp=cant[estado][pos];
		}
	}
	return resp;
}

public String eliminaIngrediente(String nombre,int estado) {
	String resp=null;
	if(estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos>=0) {
			resp=ingr[estado][pos].toString();
			MA.recorreIzq(ingr, totales[estado], pos,estado);
			totales[estado]--;
		}
	}
	return resp;
}

public static void regresaDato(double dato, int i, int j) { //metodo utilizado para regresar los datos manipulados en la funcion 'venta'
	cant[i][j]=dato;										//de la clase Restaurante, a la matriz original.
}

public void cambiarOrigen(String nombre,int estado,String origen) {
	if(estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr, totales[estado], nombre, estado);
		if(pos>=0) {
			ingr[estado][pos].setOrigen(origen);
		}
	}
}

public void cambiarPrecio(String nombre,int estado,double precio) {
	if(estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos>=0) {
			ingr[estado][pos].setPrecio(precio);
		}
	}
}

public String buscaIngrediente(String nombre,int estado) {
	String resp=null;
	if(estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos>=0) {
			resp=ingr[estado][pos].toString();
		}
	}
	return resp;
}

public double consultaStock(String nombre,int estado) {
	double resp=-1; //El -1 representa que no se encontro el ingrediente.
	if(estado>=0 && estado<4) {
		int pos=MA.busquedaBinaria(ingr,totales[estado],nombre,estado);
		if(pos>=0) {
			resp=cant[estado][pos];
		}
	}
	return resp;
}

}

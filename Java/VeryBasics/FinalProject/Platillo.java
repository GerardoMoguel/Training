/*
 * Clase platillo, determina los ingredientes que utiliza cada platillo
 */
public class Platillo {
private String nombre;
protected String []ingre;
protected Double []cantidad;
protected Integer[]estado;
private int total;
private final int MAX=50;
private double precio;

//constructores
public Platillo (String nombre, double precio) {
	ingre=new String[MAX];
	cantidad=new Double[MAX];
	estado=new Integer[MAX];
	this.nombre=nombre;
	this.precio=precio;
	for(int i=0;i<MAX;i++) {
		ingre[i]="a";
		cantidad[i]=0.0;
		estado[i]=-1;
		total=0;
	}
}

public Platillo (String nombre, double precio, int MAX) {
	this.nombre=nombre;
	this.precio=precio;
	for(int i=0;i<MAX;i++) {
		ingre[i]=null;
		cantidad[i]=0.0;
		estado[i]=0;
		total=0;
	}
}

//getters and setters
public double getPrecio() {
	return precio;
}

public void setPrecio(double precio) {
	this.precio = precio;
}

public String getNombre() {
	return nombre;
}

public int getTotal() {
	return total;
}
public String getIngre(int indice) {
	return ingre[indice];
}
public double getCantidad(int indice) {
	return cantidad[indice];
}
public int getEstado(int indice) {
	return estado[indice];
}

//funciones y metodos
public boolean altaIngrediente(String nomIng,double cant,int estad) {
	boolean resp=false;
	if(total<MAX) {
		if(total==0) {
			ingre[0]=nomIng;
			cantidad[0]=(double)cant;
			estado[0]=estad;
			total++;
			resp=true;
		}
		int pos=MA.busquedaBinaria(ingre, total, nomIng);
			if(pos<0) {
				pos=(pos+1)*-1;
				MA.recorreDer(ingre, total, pos);
				MA.recorreDer(cantidad, total, pos);
				MA.recorreDer(estado, total, pos);
				ingre[pos]=nomIng;
				cantidad[pos]=(double)cant;
				estado[pos]=estad;
				total++;
				resp=true;
			}
	}
	return resp;
}

public void cambiarCantidad(String nomIng,double cant) {
	int pos=MA.busquedaBinaria(ingre, total, nomIng);
	if(pos>=0) {
		cantidad[pos]=(double)cant;
	}
}

public String eliminaIngrediente(String nombre) {
	String resp;
		int pos=MA.busquedaBinaria(ingre, total, nombre);
		if(pos>=0) {
			resp=ingre[pos];
			MA.recorreIzq(ingre, total, pos);
			MA.recorreIzq(cantidad, total, pos);
			total--;
		}
		else
			resp=null;

	return resp;
}

public String toString() {
	StringBuilder sb=new StringBuilder();
	sb.append("Platillo: "+nombre);
	sb.append("\nPrecio: "+precio);
	if(total>0) {
	sb.append("\nIngredientes utilizados: ");
		for(int i=0;i<total;i++) {
			sb.append("\n"+ingre[i]+"  "+cantidad[i]);
		}
	}
	return sb.toString();
}

}

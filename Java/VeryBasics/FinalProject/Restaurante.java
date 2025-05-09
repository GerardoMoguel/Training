/*
 * Clase restaurante para administras los platillos y las ventas
 */
public class Restaurante {
private String nombre;
private String direccion;
protected static Platillo[]platillos;
private int totalP;
private final int MAX=150;

public Restaurante(String nombre, String direccion) {
	this.nombre=nombre;
	this.direccion=direccion;
	totalP=0;
	platillos=new Platillo[MAX];
	for(int i=0;i<MAX;i++) {
		platillos[i]=null;
	}
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getNombre() {
	return nombre;
}

public int getTotalP() {
	return totalP;
}

public boolean altaPlatillo(String nombre, double precio) {
	boolean resp=false;
	if(totalP<MAX && precio>0) {
		if(totalP==0) {
			platillos[0]=new Platillo(nombre,precio);
			totalP++;
			resp=true;
		}
		int pos=MA.busquedaBinaria(platillos, totalP, nombre);
		if(pos<0) {
			pos=(pos+1)*-1;
			MA.recorreDer(platillos, totalP, pos);
			platillos[pos]=new Platillo(nombre, precio);
			totalP++;
			resp=true;
		}
	}
	return resp;
}

public String eliminaPlatillo(String nombre) {
	String resp;
		int pos=MA.busquedaBinaria(platillos, totalP, nombre);
		if(pos>0) {
			resp=platillos[pos].toString();
			platillos[pos]=null;
			totalP--;
			MA.recorreIzq(platillos, totalP, pos);
		}
		else
			resp=null;
	
	return resp;
}

public String venta(String nombre,int cantidad,double[][]cantidades,Ingredientes[][]ing) {
	StringBuilder resp=new StringBuilder();
	String nom;
	int estado,pos,pos2;
	pos=MA.busquedaBinaria(platillos, totalP, nombre);
	if(pos>=0 && cantidad>0) {
		for(int i=0;i<platillos[pos].getTotal();i++) {
			nom=platillos[pos].getIngre(i);
			estado=platillos[pos].getEstado(i);
			pos2=MA.busquedaBinaria(ing,Stock.getTotales(estado),nom,estado);
			if(pos2>=0) {
				cantidades[estado][pos2]-=platillos[pos].getCantidad(i)*cantidad;
				resp.append("\nIngrediente "+nom+" actualizado con exito");
				Stock.regresaDato(cantidades[estado][pos2], estado, pos2);
				if(cantidades[estado][pos2]>0.2 && cantidades[estado][pos2]<1) {
					resp.append("\nQuedan menos de 1 kg/L de "+nom+", se recomienda ordenar mas producto\n");
				}
				if(cantidades[estado][pos2]>0 && cantidades[estado][pos2]<0.2) {
					resp.append("\nHay menos de 200 gr/ml "+nom+", se recomienda urgentemente ordenar mas producto\n");
				}
				if(cantidades[estado][pos2]==0) {
					resp.append("\nNo hay "+nom+" en stock, se recomienda ordenar mas producto\n");
				}
				if(cantidades[estado][pos2]<0) {
					resp.append("\n"+nom+" en Stock negativo. Actualiza stock.\n"); //no se genera ningun error ya que si el usuario pudo vender el producto
				}														//es porque aun hay en stock, solamente necesita agregar mas al stock.
			}
		}
	}
	else
		resp.append(false);
return resp.toString();
}


}

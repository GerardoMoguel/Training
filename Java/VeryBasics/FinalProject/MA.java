/*
 * Clase para manejar arreglos bidimensionales y unidimensionales
 */
public class MA {

public static int busquedaBinaria(Ingredientes[][]arr,int total,String dato,int tipo) {
	int izq=0;
	int der=total-1;
	int mid=(der+izq)/2;
	Ingredientes ing=new Ingredientes(dato);
	while(izq<=der && !ing.equals(arr[tipo][mid])) {
		if(dato.compareTo(arr[tipo][mid].getNombre())>0)
			izq=mid+1;
		else
			der=mid-1;
		mid=(izq+der)/2;
		if (izq>der)
			mid=-(izq+1);
	}
	return mid;
}

public static <T extends Comparable<T>> int busquedaBinaria(T[]arr,int total,T dato) {
	int izq=0;
	int der=total-1;
	int mid=(der+izq)/2;
	while(izq<=der && !dato.equals(arr[mid])) {
		if(dato.compareTo(arr[mid])>0)
			izq=mid+1;
		else
			der=mid-1;
		mid=(izq+der)/2;
		if (izq>der)
			mid=-(izq+1);
	}
	return mid;
}

public static int busquedaBinaria(Platillo[]arr,int total,String dato) {
	int izq=0;
	int der=total-1;
	int mid=(der+izq)/2;
	while(izq<=der && !dato.equals(arr[mid].getNombre())) {
		if(dato.compareTo(arr[mid].getNombre())>0)
			izq=mid+1;
		else
			der=mid-1;
		mid=(izq+der)/2;
		if (izq>der)
			mid=-(izq+1);
	}
	return mid;
}

public static <T>void recorreIzq(T[]arr,int total,int pos) {
	for(int i=pos;i<total;i++)
		arr[i]=arr[i+1];
}
public static <T>void recorreIzq(T[][]arr,int total,int pos,int estado) {
	for(int i=pos;i<total;i++)
		arr[estado][i]=arr[estado][i+1];
}
public static <T>void recorreDer(T[][]arr,int total,int pos,int estado) {
	for(int i=total;i>pos;i--)
		arr[estado][i]=arr[estado][i-1];
}

public static <T>void recorreDer(T[]arr,int total,int pos) {
	for(int i=total;i>pos;i--)
		arr[i]=arr[i-1];
}

}

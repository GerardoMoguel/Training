import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Gerardo Andres Moguel Rovelo
 * Manejador arreglos
 */

public class ManejadorArreglos {
	
	//metodos y funciones
public static double sumaArr(int[]arr,int total) {
	double suma=0;
	for(int i=0;i<total;i++) 
		suma=suma+arr[i];
	return suma;
}

public static double promArr(double[]arr,int total) {
	double suma=0;
	for(int i=0;i<total;i++) 
		suma=suma+arr[i];
	return suma/total;
}

public static int indiceMayor(double[]arr,int total) {
	int indice=-1;
	double dato=Math.pow(-9,19);//numero muy pequenio
	for(int i=0;i<total;i++) 
		if(arr[i]>dato) {
			indice=i;
			dato=arr[i];
		}
	return indice;
}

public static int indiceMenor(double[]arr,int total) {
	int indice=-1;
	double dato=Math.pow(10,20);//numero muy grande
	for(int i=0;i<total;i++) 
		if(arr[i]<dato) {
			indice=i;
			dato=arr[i];
		}
	return indice;
}

public static int mayorA(double[] ventas,int total, double parametro) {
	int cont=0;
	for(int i=0;i<total;i++) 
		if(ventas[i]>parametro)
			cont++;
	return cont;
}

public static int menorA(double[]arr,int total, double parametro) {
	int cont=0;
	for(int i=0;i<total;i++) 
		if(arr[i]<parametro)
			cont++;
	return cont;
}

public static void leeArr(int[]arr,int total, Scanner lee) {
	for(int i=0; i<total;i++) {
		System.out.print("Ingrese un dato: ");
		arr[i]=lee.nextInt();
	}
}
public static void recorreIzq(double[]arr,int total,int pos) {
	for(int i=pos;i<total;i++)
		arr[i]=arr[i+1];
}

public static void recorreDer(double[]arr,int total,int pos) {
	for(int i=total;i>pos;i--)
		arr[i]=arr[i-1];
}

public static int insertaOrdenado(double[]arr,int total,double dato) {
	if(total<arr.length) {//hay espacio
		int posicion;
		posicion=busquedaBinaria(arr,total,dato);//busqueda binaria porque esta ordenado
		if(posicion<0) {//queremos que sea negativo porque significa que no encontro a nadie
			posicion=posicion*-1-1;
			recorreDer(arr,total,posicion);
			arr[posicion]=dato;
			total++;
		}
	}
	return total;
}
//double, se utilia en arreglos desordenados
public static int busquedaSecuencial(double[]arr,int total,double dato) {
	int pos=-1;
	for(int i=0;i<total;i++) 
		if(arr[i]==dato)
			pos=i;
	return pos;
}

//string, se utiliza en arreglos desordenados
public static int busquedaSecuencial(String[]arr,int total,String dato) {
	int pos=-1;
	for(int i=0;i<total;i++) 
		if(arr[i].equals(dato))
			pos=i;
	return pos;
}


public static int busquedaBinaria(double[]arr,int total,double dato) {
	int izq=0,der=total-1,mid=(der+izq)/2;
	while(izq<=der && dato!=arr[mid]) {
		if(dato>arr[mid])
			izq=mid+1;
		else
			der=mid-1;
		mid=(izq+der)/2;
		if (izq>der)
			mid=-(izq+1);
	}
	return mid;
}

public static int eliminaDato(double[]arr, int total, double dato) {
	int pos;
	pos=busquedaSecuencial(arr,total,dato);
	if(pos!=-1) {
		arr[pos]=arr[total-1];
		total--;
	}
	return total;
}

public static int eliminaOrdenado(double[]arr,int total, double dato) {
	int pos;
	pos=busquedaBinaria(arr,total, dato);
	if(pos>0){//si existe
		recorreIzq(arr,total,pos);
		total--;
	}
	return total;
}

public static int[] ordenaArr(int[]arr,int total){
	for(int i=0;i<total-1;i++) {
		int pos=i;
		int min=arr[i];
		for(int j=i+1;j<total;j++) {
			if(arr[j]<min) {
				min=arr[j];
				pos=j;
			}
			arr[pos]=arr[i];
			arr[i]=min;
		}
	}
	return arr;
}

	public static void main(String[] args) {
Scanner lee=new Scanner(System.in);
//int lenght=0;
//double parametro=0;
//System.out.print("Ingrese la cantidad de dias que registrara ingresos: ");
//lenght=lee.nextInt();
//int[]arreglo=new int [lenght];
//double[]arr1= {1,2,3,4,5,6,7,8};
//leeArr(arreglo,lenght,lee);
//System.out.println("La suma de los datos es: "+sumaArr(arreglo,lenght));
//System.out.println("El promedio de los datos es: "+promArr(arreglo,lenght));
//System.out.println("El indice con el valor mas grande es: "+indiceMayor(arreglo,lenght));
//System.out.print("Por favor, ingrese el valor que sera el parametro de discriminacion: ");
//parametro=lee.nextDouble();
//System.out.println("La cantidad de datos mayores al parametro es de: "+mayorA(arreglo,lenght,parametro));
//System.out.println(busquedaBinaria(arr1,8,3));
double[]arr2= {1,0,0};
System.out.println(busquedaBinaria(arr2,0,3));
//System.out.println(eliminaOrdenado(arr2,8,8));
//System.out.println(eliminaOrdenado(arr2,eliminaOrdenado(arr2,eliminaOrdenado(arr2,8,8),7),2)); //el eliminado del eliminado del eliminado xd
//System.out.println(busquedaBinaria(arr2,8,3));
//System.out.println(insertaOrdenado(arr2,7,3));
//System.out.println(Arrays.toString(arr2));
	}//main

}//class


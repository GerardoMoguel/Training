import java.util.Scanner;

/*
 * Gerardo Moguel
 */
public class ProcesaCalificaciones {
public static void leeArreglo(int [] calif, int total, Scanner lee) {
	
	for(int i=0;i<total;i++) {
		System.out.print("Ingresa una calificacion: ");
		calif[i]=lee.nextInt();
	}
}
public static double calculaPromedio(int[]cal,int total) {//es regla que siempre se pone la cantidad de datos guardados en el arreglo como parametro
	int suma=0;
	for(int i=0;i<total;i++) {
		suma=suma+cal[i];
	}
	return suma/total;
}
public static int cuentaMayoresQue(int[] cal,int total, double promedio) {
	int cont=0;
	for(int i=0;i<total;i++) {
		if(cal[i]>promedio)
			cont++;
	}
	return cont;
}
public static void main(String[] args) {
	Scanner lee = new Scanner (System.in);
	int total;
	double promedio=0;
	int[] calificaciones;
	calificaciones=new int[50];
	
	do {
		System.out.print("Ingresa total de alumnos: ");
		total=lee.nextInt();
	}while (total<=0 || total>50);
	
	leeArreglo(calificaciones,total,lee);
	promedio=calculaPromedio(calificaciones,total);
	System.out.println("\nEl promedio = "+promedio);
	System.out.println("\nTotal de calificaciones>prom: "+cuentaMayoresQue(calificaciones,total,promedio));
	
}//main
}//class

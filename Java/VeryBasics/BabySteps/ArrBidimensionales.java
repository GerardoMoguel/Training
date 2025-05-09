import java.io.File;
import java.util.Scanner;

//Gerardo M

public class ArrBidimensionales {
	public static int calProm(int[][]arr,int rengl) {
		int total=0;
		for(int i=0;i<arr[0].length;i++)
			total=total+arr[rengl][i];
		return total/arr[0].length;
	}
	
public static void main (String[]args) {
	int[][]calif=new int [10][3];
try {
	File archivo=new File("Calificaciones");
	Scanner lee= new Scanner (archivo);
	for(int i=0;i<10;i++)
		for(int j=0;j<3;j++) 
			calif[i][j]=lee.nextInt();
	lee.close();
}catch (Exception e) {
	System.out.println("No se pudo abrir el archivo");
	System.exit(0);
}
for(int i=0;i<calif.length;i++)
System.out.println("La calificacion promedio del alumno "+(i+1)+" es igual a: "+calProm(calif,i));
}
}

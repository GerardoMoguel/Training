import java.util.Arrays;

/*
 * Manejador de arreglos bidimensionales generico
 * Gerardo Moguel
 * Seccion 10, ejercicio 5
 */
public class MABG {
	

public static <T> int[] searchElement(T[][]arr,T dato,int totalR, int totalC){
	int pos[]= {-1,-1};
	for(int i=0;i<totalR;i++)
		for(int j=0;j<totalC;j++) 
			if(dato.equals(arr[i][j])) {
				pos[0]=i;
				pos[1]=j;
			}
	return pos;
}

public static <T> String toStringArr(T[][]arr,int totalR,int totalC) {
	StringBuilder sb=new StringBuilder();
	for(int i=0;i<totalR;i++) {
		for(int j=0;j<totalC;j++) {
			sb.append(arr[i][j]);
			sb.append(" ");
		}
		sb.append("\n");
	}
	return sb.toString();
}

public static <T extends Comparable<T>> int[] posElementoGrande(T[][]arr,T dato, int totalR, int totalC) {
	T grande=arr[0][0];
	int[]pos= {0,0};
	for(int i=0;i<totalR;i++)
		for(int j=1;j<totalC;j++) {
			if(grande.compareTo(dato)>0) {
				grande=arr[i][j];
				pos[0]=i;
				pos[1]=j;
			}
		}
	return pos;
}

public static <T> boolean ifEqualsArr (T[][]arr1,T[][]arr2,int totalR1,int totalC1, int totalR2,int totalC2) {
	boolean flag=true;
	int col,ren=0;
	if(totalR1==totalR2 && totalC1==totalC2) {
		while(ren<totalR1 && flag) {
			col=0;
			if(!arr1[ren][col].equals(arr2[ren][col]))
				flag=false;
			else
				col++;
		ren++;
		}
		return flag;
	}
	else
		return false;
}

//main
public static void main (String []args) {
	Integer[][]m1 = {{1,2,3},{4,5,6},{7,8,9}};
	Integer num=2;
	System.out.println(Arrays.toString(searchElement(m1,6,m1.length,m1[0].length)));//m1[0].length sirve para la cantidad de columnas
	System.out.println(toStringArr(m1,m1.length,m1[0].length));
	
}
}

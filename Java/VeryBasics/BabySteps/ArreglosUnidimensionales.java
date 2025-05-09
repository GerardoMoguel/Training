import java.util.Arrays;

/*
 * Gerardo A. Moguel Rovelo
 * Tarea de arreglon unidimensionales
 */
public class ArreglosUnidimensionales {

//ejercicio 1
	public static boolean isIntNTimes(int[]arr,int total,int data,int n) {
		boolean resp=false;
		int counter=0;
		for(int i=0;i<total;i++) {
			if(arr[i]==data && arr[i]!=arr[i-1]) {
				counter=counter+1;
			}
		}
		if (counter==n)
			resp=true;
		return resp;
	}
	
//ejercicio 2
	public static boolean isEverythingN1N2(int[]arr,int total, int n1, int n2) {
		boolean resp=true;
		for(int i=0;i<total;i++) {
			if(arr[i]!=n1 && arr[i]!=n2)
				resp=false;
		}
		return resp;
	}
	
//ejercicio 3
	public static boolean is3Consecutives(int[]arr,int total) {
		boolean resp=false;
		for(int i=0;i<total;i++) {
			if(i<total-2) {
			if(arr[i]+1==arr[i+1])
				if(arr[i]+2==arr[i+2])
					resp=true;
			}
		}
		return resp;
	}
	
//ejercicio 4
	public static int[] changeLonelyNum(int[]arr,int total,int data) {
	int[]array=new int [total];
	for(int i=0;i<total;i++) {
		array[i]=arr[i];
		if(i<total-1 && i>0) 
		if(arr[i]!=arr[i-1] && arr[i]!=arr[i+1] && arr[i]==data)
			array[i]=arr[i+1];
	}
	return array;
	}
	
	public static void main(String[] args) {
		
		//prueba ejercicio 1, con integers consecutivos.
		int[]arr1= {1,2,2,2,3,4,2,6};
		System.out.println("El arr1 tiene al integer 2, 4 veces sin ser consecutivo? "+isIntNTimes(arr1,8,2,4));
		
		//prueba ejercicio 1, con integers separados.
		int[]arr11= {1,2,5,2,3,4,2,6};
		System.out.println("El arr11 tiene al integer 2, 3 veces sin ser consecutivo? "+isIntNTimes(arr11,8,2,3));
		
		
		//prueba ejercicio 2.
		int[]arr2= {2,1,2,2,1,2,1,2};
		System.out.println("\nTodos los elementos del arr2: son 1 o 2? "+isEverythingN1N2(arr2,8,1,2));
		
		//prueba ejercicio 2 con numeros distintos.
		int[]arr22= {2,1,2,2,1,3,1,2};
		System.out.println("Todos los elementos del arr22: son 1 o 2? "+isEverythingN1N2(arr22,8,1,2));
		
		
		//prueba ejercicio 3.
		int[]arr3= {1,3,2,2,4,6,7,8};
		System.out.println("\nEl arr3, tiene una secuencia de 3 numeros consecutivos? "+is3Consecutives(arr3,8));
		
		//prueba ejercicio 3 sin numeros consecutivos.
		int[]arr33= {1,3,2,2,4,1,7,8};
		System.out.println("El arr33, tiene una secuencia de 3 numeros consecutivos? "+is3Consecutives(arr33,8));
		
		
		//prueba ejercicio 4
		int[]arr4= {2,3,6,4,2,6,9};
		System.out.println("\nEl arr4, sin el valor 6 es: "+Arrays.toString(changeLonelyNum(arr4,7,6)));
		//prueba ejercicio 4 con el numero junto al mismo
		int[]arr44= {2,3,6,6,2,6,9};
		System.out.println("El arr44, sin el valor 6 es: "+Arrays.toString(changeLonelyNum(arr44,7,6))); //no cambia el que es consecutivo
	}
}//class

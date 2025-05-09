import java.util.ArrayList;

public class PracticaArreglos {
public static ArrayList<Integer> encuentraParesImpares(int[]par,int[]impar){
	ArrayList<Integer> res=new ArrayList();
	for(int i=0;i<par.length;i++)
		if(par[i]%2==0)
			res.add(par[i]);
	for(int j=0;j<impar.length;j++)
		if(impar[j]%2!=0)
			res.add(impar[j]);
	return res;
}

public static ArrayList<Integer> obtenPrimos2aN(int n){
	int[]arr=new int[n+1];
	ArrayList<Integer> res=new ArrayList();
for(int i=0;i<arr.length;i++)
	arr[i]=1;
for(int j=2;j<arr.length;j++) {
	int k=2;
	while(arr[j]!=0 && k<=arr.length) {
		if (k==j)
			k=k+1;
		if(j%k==0)
			arr[j]=0;
	k++;
	}
}
for(int x=2;x<arr.length;x++) {
	if(arr[x]==1)
		res.add(x);
}
return res;
}

	public static void main (String[]args) {
		int arr1[]= {1,2,3,4,5,6,7,8,9};
		int arr2[]= {1,2,3,4,5,6,7,8,9}; //en las pruebas prueba todo, prueba un arr de pares sin pares, prueba dos arrs vacios. No deberia de tronar. 
		System.out.println(obtenPrimos2aN(10));
		System.out.println(encuentraParesImpares(arr1,arr2));
		
	}//main

}//class

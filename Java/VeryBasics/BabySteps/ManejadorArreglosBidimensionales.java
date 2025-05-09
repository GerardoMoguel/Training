import java.util.Arrays;
import java.util.Scanner;

/*
 * Gerardo M
 * funciones y metodos de arreglos bidimensionales
 */
public class ManejadorArreglosBidimensionales {
public static double sumaRenglon(double[][]arr,int ren,int totalCol) {
	double suma=0;
	for(int i=0;i<totalCol;i++)
		suma+=arr[ren][i];
	return suma;
}

public static double sumaColumna(double[][]arr,int col,int totalRen) {
	double suma=0;
	for(int i=0;i<totalRen;i++)
		suma+=arr[i][col];
	return suma;
}

public static double sumaArr(double[][]arr,int totalCol,int totalRen) {
	double suma=0;
	for(int j=0;j<totalRen;j++)
		for(int i=0;i<totalCol;i++)
		suma+=arr[j][i];
	return suma;
}

public static double sumaDiagonal(double[][]arr,int total) {
	int suma=0;
	for(int i=0;i<total;i++)
		suma+=arr[i][i];
	return suma;
}

public static double sumaDiagonal2(double[][]arr,int total) {
	int suma=0,a=0;
	for(int i=total-1;i>=0;i--) {
		suma+=arr[a][i];
		a++;
	}
	return suma;
}

public static String acomodaRectangular(double[][]arr,int totalCol,int totalRen) {
	StringBuilder sb=new StringBuilder();
	if((totalRen*totalCol)%2==0) {
	for(int i=0;i<totalRen;i++)
		for(int j=0;j<totalCol;j++) {
			sb.append(arr[i][j]);
			sb.append(" ");
			if((totalRen/2)-1==i&&(totalCol/2)==j)
				sb.append("\n");
		}
	}
	else 
		for(int i=0;i<totalRen;i++)
			for(int j=0;j<totalCol;j++) {
				sb.append(arr[i][j]);
				sb.append(" ");
			}
	
	return sb.toString();
}

public static int[] posPequeCol(double[][]arr,int totalRen,int col) {
	int []pos= {-1,-1};
	double peque=Math.pow(10, 1234);
		for(int j=0;j<totalRen;j++)
			if(arr[j][col]<peque) {
				peque=arr[j][col];
				pos[1]=col-1;
				pos[0]=j;
			}
		
	return pos;
}

public static int[] posMayorCol(double[][]arr,int totalRen,int col) {
	int []pos= {-1,-1};
	double gran=-99999;
		for(int j=0;j<totalRen;j++)
			if(arr[j][col]>gran) {
				gran=arr[j][col];
				pos[1]=col-1;
				pos[0]=j;
			}
		
	return pos;
}

public static int[] posPequeRen(double[][]arr,int totalCol,int ren) {
	int []pos= {-1,-1};
	double peque=Math.pow(10, 1234);
		for(int j=0;j<totalCol;j++)
			if(arr[ren][j]<peque) {
				peque=arr[ren][j];
				pos[1]=j;
				pos[0]=ren-1;
			}
		
	return pos;
}

public static int[] posMayorRen(double[][]arr,int totalCol,int ren) {
	int []pos= {ren,-1};
	double gran=-99999;
		for(int j=0;j<totalCol;j++)
			if(arr[ren][j]>gran) {
				gran=arr[ren][j];
				pos[1]=j;
			}
		
	return pos;
}

public static double[][] sumaArrs(double[][]arr1,int ren1,int col1,double[][]arr2,int ren2, int col2){
	double[][] arr;
	if(ren1==ren2 && col1==col2 &&ren1>0 && col1>0) {
		arr=new double [ren1][col1];
		for(int i=0;i<ren1;i++)
			for(int j=0;j<col1;j++) 
				arr[i][j]=arr1[i][j]+arr2[i][j];	
	}
	else 
		arr=null;
	return arr;
}

public static double[][] multiArrays(double[][]arr1,int ren1,int col1,double[][]arr2,int ren2, int col2){
	double[][] arr=new double [ren1][col2];
	int r=0,a=0;
	if(col1==ren2) {
		while(r<ren1) {
			double suma=0;
			for(int j=0;j<col2;j++) {
				suma+=(arr1[r][j]*arr2[j][a]);	
			}
			arr[r][a]=suma;	
			if(a<col2) {
				a++;
			}
			if(a==col2) {
				a=0;
				r++;
			}
		}	
	}
	return arr;
}

public static double[][] genTraspuesta(double[][]arr1,int ren,int col){
	double[][] arr=new double [col][ren];
	for(int i=0;i<ren;i++)
		for(int j=0;j<col;j++)
			arr[j][i]=arr1[i][j];
	return arr;
}

public static boolean isSymetric(double[][]arr1,int ren,int col) {
	double[][]trasp=genTraspuesta(arr1,ren,col);
	int i=0;
	boolean flag=true;
	if(ren==col&&ren>0&&col>0) { 
	while(flag&&i<ren)
		for(i=0;i<ren;i++)
			for(int j=0;j<col;j++)
				if(arr1[i][j]!= trasp[i][j])
					flag=false;
	}
	else
		flag=false;	
	return flag;
}
public static boolean isSymetricMejor(double[][]arr1,int total) {//la otra hacia mucho trabajo
	boolean resp=true;
	int i=0;
	while(i<total&&resp) {
		int j=i+1;
		while(j<total&& resp)
			if(arr1[i][j]!=arr1[j][i])
				resp=false;
			else
				j++;
	i++;
	}
	return resp;
}

public static boolean isIdentidadMal(double[][]arr1,int ren, int col) { //esta mal
    boolean flag=true;
    int i=0;
    if(ren==col) 
        while(flag&&i<ren)
            for(i=0;i<ren;i++)
                for(int j=0;i<col;j++) {
                    while (i!=j) 
                        if(arr1[i][j]!=0)
                            flag=false;
                    while (i==j)
                        if(arr1[i][j]!=1)
                            flag=false;
                }
    else
        flag=false;
return flag;
}

public static boolean isIdentidad(double[][]arr1,int ren, int col) {
	boolean resp=true;
	if(col==ren&&col>0&&ren>0) {
		for(int i=0;i<ren;i++) {
			for(int j=0;j<col;j++) {
				if(i==j&&arr1[i][j]!=1) {
					resp=false;
				}
				if(i!=j&&arr1[i][j]!=0) {
					resp=false;
				}
			}
		}
	}
	else
		resp=false;
	return resp;			
}

public static void main (String[]args) {
	double [][]arr= {{1,2,3,2},{12,3,21,1},{12,2,3,9}};
	double [][]arr1= {{7,3,4,3},{13,4,22,3},{13,3,4,3},{1,2,3,3}};
	double [][]arr2= {{1,1,5},{1,2,3},{5,3,4}};
	double[][]arr3= {{1,0,0},{0,1,0},{0,0,1}};
	
	//System.out.println("El arreglo es:");
	//System.out.println(Arrays.deepToString(arr));
	
	//System.out.println("\n"+acomodaRectangular(arr,3,3));
	//System.out.println("\n"+Arrays.toString(posMayorRen(arr,3,2)));
	//System.out.println("\nLa suma es: "+Arrays.deepToString(sumaArrs(arr,3,4,arr1,3,4)));
	
	System.out.println("\nEl resultado de la multiplicacion es: "+Arrays.deepToString(multiArrays(arr,3,4,arr1,4,4)));
	
	//System.out.println("\n El array traspuesto: "+Arrays.deepToString(genTraspuesta(arr1,4,4)));
	
	//System.out.println("\nEs simetrica? "+isSymetric(arr2,3,3));
	//System.out.println("Es simetrica? "+isSymetric(null,0,0));
	
	//System.out.println("\nEs la identidad? "+isIdentidad(arr3,3,3));
	//System.out.println("Es la identidad? "+isIdentidad(null,0,0));
	
	
}

}

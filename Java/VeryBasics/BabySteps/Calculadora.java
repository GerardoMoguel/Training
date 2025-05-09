
/*
 * clase de calculadora ejercicios de los problemas
 * Gerardo Moguel
 */
public class Calculadora {
//ejercicio 27
	public static String generaCubosNicomaco(int n) {
		StringBuilder lele= new StringBuilder();
		int antique=-1;
		for (int i=1;i<=n;i++) {
			int num=0;
			int sum=0;
				for (int z=1;z<=i;z++) {
					num=antique+2;
					sum=num+sum;
					lele.append(num).append(" ");
					antique=num;
				}
				lele.append(" = "+sum+" = "+sum/(i*i)+"^3");
				lele.append("\n ");
		}
		return lele.toString();
	}
	
	public static String generaSerieFibonacci(int n) {
		int primer=0;
		int segundo=1;
		String toString=new String();
		for (int i=0;i<n;i++) {
			int a=0;
			a=primer+segundo;
			primer=segundo;
			segundo=a;
			System.out.print(segundo+" ");
		}
		return toString;
	}
	
	public static double calculaSerie2(int n, double numero) { //puse double en numero porque int/int da de resultado un int, y necesitamos los decimales
		double total=0;
		double a=3.0;
		for (int i=0;i<n;i++) {
			total=total+numero/a;
			a=a+2;
			if(a>7)
				a=3;
		}
		return total;
	}
	
	public static double calculaFactorial(double n) {
		double total=n;
		if(n==0)
			total=1;
		while(n>=0) {
			for (int i=1;i<n;i++) {
				total=total*(n-i);
			}
			return total;
		}
		return total;
	}
	
	public static double calculaSerie3(double n) {
		double total=0;
		for(int i=0;i<=n;i++){
			double den=Calculadora.calculaFactorial(i);
			double num=Calculadora.calculaFactorial(n-i);
			total=total+num/den;
			}
		return total;
	}
	
	public static boolean esDivisiblePor11(int n) { //era sin arreglos
		boolean resp=false;
		int par=0;
		int impar=0;
		    String str = (new Integer(n)).toString();
		    char[] chArr = str.toCharArray();
		    int[] arr = new int[chArr.length];
		    for (int i = 0; i< chArr.length; i++) {
		        arr[i] = Character.getNumericValue(chArr[i]);
		    }
		    for(int j=0;j<chArr.length;j++) {
		    	if(j%2==0)
		    		par=par+arr[j];
		    		else
		    			impar=impar+arr[j];
		    }
		    if ((par-impar)%11==0)
		    	resp=true;
		    else
		    	resp=false;
		    return resp;
	}
	
	
}//class

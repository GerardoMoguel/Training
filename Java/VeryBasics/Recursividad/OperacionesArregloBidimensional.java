package Recursividad;

import java.io.File;
import java.util.Scanner;

public class OperacionesArregloBidimensional {
	
	public static int cuentaPalabras() {
	int cont=0;
	try {
		Scanner lee=new Scanner(new File("Nom"));
		cont=cuentaPalabras(lee, cont);
		lee.close();
	}catch(Exception e){
		cont=-1;
	}
	return cont;
	}
	
	private static int cuentaPalabras(Scanner lee, int resp) {
		if(lee.hasNext()) {	
			lee.next();
			resp+=1;
			return cuentaPalabras(lee, resp);
		}
		else {
			return resp;
		}
	}
	
	public static void main (String[]args) {
		System.out.println(cuentaPalabras());
	}
	
}


public class MAG {
public static <T> int buscaSecuencial(T[]arr1, int total,T datos) {
	int i=0;
	while(i<total&&!arr1.equals(datos))
		i++;
	if(i==total)
		i=-1;
	return i;
}

public static void main (String[]args) {
	Integer[]num= {2,6,4,8,9,16};
	String []colores= {"Rojo","Negro","Rosa","Celeste","Turquesa"};
	int pos,pos2;
	pos=MAG.buscaSecuencial(num, num.length, 9);
	System.out.println("El dato esta en: "+pos);
	
	pos2=MAG.buscaSecuencial(colores, colores.length, "Negro");
	System.out.println("El color esta en: "+pos2);
	
}
}

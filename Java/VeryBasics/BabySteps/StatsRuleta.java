import java.util.ArrayList;

/*
 * Gerardo Moguel
 *Seccion 9. Ejercicio 8, estadisticas sobre el juego de la ruleta. 
 */
public class StatsRuleta {
private int [] rojos;
private int [] negros;
private int [] verdes;

public StatsRuleta() {
	rojos=new int[34];
	negros=new int[34];
	verdes=new int[2];
	
	for(int i=0;i<34;i++) 
		rojos[i]=0;
	
	for(int i=0;i<34;i++) 
		negros[i]=0;
	
	for(int i=0;i<2;i++) 
		verdes[i]=0;
}

public int getRojo(int i) {
	int resp=-1;
	if(i>0 && i<=34)
	resp= rojos[i-1];
	return resp;
}

public int getNegro(int i) {
	int resp=-1;
	if(i>0 && i<=34)
	resp= negros[i-1];
	return resp;
}

public int getVerde(int i) {
int resp=-1;
if(i==2)
	resp=verdes[0];
else if(i==1)
	resp=verdes[1];
return resp;
}

public boolean altaDatos(String color, int num, int cantidad) {
	boolean resp=false;
	if(num<=34 && num>0 && cantidad>0)
		if(color.equals("rojo")||color.equals("negro")) {
			resp=true;
		switch(color) {
		case "rojo": rojos[num-1]=rojos[num-1]+cantidad;
			break;
		case "negro": negros[num-1]=negros[num-1]+cantidad;
			break;
		}
		}
	if(color.equals("verde")&& cantidad>0) 
		if(num==1 || num==2) {
			resp=true;
			if(num==2)
				verdes[0]=verdes[0]+cantidad;
			else if(num==1)
				verdes[1]=verdes[1]+cantidad;
		}
	return resp;
}

public int winNum(String color, int i) {
	int resp=-1;
	if(i<=34 && i>0 ){
		if(color.equals("rojo")||color.equals("negro"))
			switch(color) {
			case "negro": resp=negros[i-1];
				break;
			case "rojo": resp=rojos[i-1];
				break;
			}
	}
	if(color.equals("verde"))
		if(i==1||i==2)
			if(i==2)
				resp=verdes[0];
			else if(i==1)
				resp=verdes[1];
	return resp;
}

public String winNumbers() {
	StringBuilder sb=new StringBuilder();
	
	for (int u=0;u<34;u++) 
		sb.append("El numero: "+(u+1)+" de color rojo, ").append("gano "+rojos[u]+" veces.\n");
	
	for (int x=0;x<34;x++) 
		sb.append("El numero: "+(x+1)+" de color negro, ").append("gano "+negros[x]+" veces.\n");
	

		sb.append("El numero: 00 de color verde, ").append("gano "+verdes[0]+" veces.\n");
		sb.append("El numero: 0 de color verde, ").append("gano "+verdes[1]+" veces.\n");

	return sb.toString();
}

public String notWinNum() {
	StringBuilder sb= new StringBuilder();
	for(int i=0;i<34;i++)
		if(rojos[i]==0)
			sb.append("El numero "+(i+1)+" de color rojo no gano ninguna vez.\n");
	for(int j=0;j<34;j++)
		if(negros[j]==0)
			sb.append("El numero "+(j+1)+" de color negro, no gano ninguna vez.\n");
	for(int k=0;k<2;k++)
		if(verdes[k]==0) {
			if(k==0)
			sb.append("El numero 00 de color verde, no gano ninguna vez\n");
			else if (k==1)
				sb.append("El numero 0 de color verde, no gano ninguna vez\n");
		}
	return sb.toString();
}

public String numWinColor() {
	StringBuilder sb=new StringBuilder();
	int r=-1,n=-1,v=-1;
	int posR=0,posN=0,posV=0;
	for(int i=0;i<34;i++)
		if(rojos[i]>r) {
			r=rojos[i];
			posR=i+1;
		}
	for(int i=0;i<34;i++)
		if(negros[i]>n) {
			n=negros[i];
			posN=i+1;
		}
	for(int i=0;i<2;i++)
		if(verdes[i]>v) {
			v=verdes[i];
			if(i==0)
			posV=2;
			else
				posV=1;
		}
	sb.append("El numero rojo que con mayor frecuencia es el numero: "+posR+"\n");
	sb.append("El numero negro que con mayor frecuencia es el numero: "+posN+"\n");
	sb.append("El cero verde con mayor frecuencia es el que tiene: "+posV+" cero(s)\n");
return sb.toString();
}

public int numMoreTimes() {
	int a=-1,pos=-1;
	ArrayList<Integer> nums= new ArrayList();
	for(int i=0;i<34;i++) 
		nums.add(rojos[i]+negros[i]);
	
	for(int i=0;i<34;i++) 
		if(nums.get(i)>a) {
			a=nums.get(i);
			pos=i+1;
		}
	if (verdes[0]>a) {
		pos=00;
		a=verdes[0];
	}
	if(verdes[1]>a)
		pos=0;
	return pos;
}

public boolean isWinParMasQueImparColor(String color) {
	int par=0;
	int impar=0;
	boolean a=false;
	boolean resp=false;
	switch(color) {
	case "rojo":{
		for(int i=0;i<34;i++) 
		if(a) {
			par=par+rojos[i];
		a=false;;
		}
		else {
			impar=impar+rojos[i];
			a=true;;
		}
	}
		break;
	case "negro":
		for(int i=0;i<34;i++) {
			if(i%2==0)
				par=par+negros[i];
			else
				impar=impar+negros[i];
			}
		break;
	case "verde":
		resp=true;
		break;
	}
	if (par>impar)
		resp=true;
	return resp;
}

public static void main (String args[]) {
	StatsRuleta uno=new StatsRuleta();
	uno.altaDatos("verde", 1,20);
	uno.altaDatos("rojo", 2,1);
	uno.altaDatos("negro", 3,5);
	uno.altaDatos("verde", 1,4);
	uno.altaDatos("rojo", 24,500);
	uno.altaDatos("negro", 12,3);
	uno.altaDatos("verde", 2,2);
	uno.altaDatos("rojo", 12,60000);
	uno.altaDatos("negro", 31,4);
	uno.altaDatos("verde", 1,100);
	uno.altaDatos("rojo", 34,6);
	uno.altaDatos("negro", 21,2);
	uno.altaDatos("verde", 2,5);
	uno.altaDatos("rojo", 9,7);
	uno.altaDatos("negro", 33,22);
	uno.altaDatos("negro", 34,-22);
	uno.altaDatos("verde", 2,1200); //en los verde no se pone el numero que quieres (00,0) se pone la cantidad de ceros.
	uno.altaDatos("negro", 8,2000);
	uno.altaDatos("negro", 8,2200);
	
	
	System.out.println(uno.isWinParMasQueImparColor("rojo"));
	System.out.println(uno.isWinParMasQueImparColor("negro"));
	System.out.println(uno.isWinParMasQueImparColor("brazil"));


	System.out.println(uno.numWinColor());
	System.out.println("El numero que se repitio mas veces fue el: "+uno.numMoreTimes()+"\n");
	System.out.println(uno.notWinNum());
	System.out.println(uno.winNumbers());
	
	System.out.println(uno.getVerde(12));
	System.out.println(uno.getVerde(1));

}//main
}//class

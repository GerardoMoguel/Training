
public class Cia {
private String nombre;
private double [][] ventas;
private final int MESES=12;
private final int DPTOS=4;

public Cia() {
	ventas=new double[MESES][DPTOS];
	for(int i=0;i<MESES;i++)
		for(int j=0;j<DPTOS;j++)
			ventas[i][j]=0;
}

public Cia(String nombre) {
	this();
	this.nombre = nombre;
}

public boolean altaDatos(int mes, int dep, double venta) {
	boolean resp=false;
	if(mes>0 && mes<=MESES && dep>0 && dep<=DPTOS) {
		mes-=1;
		dep-=1;
		ventas[mes][dep]=venta;
		resp=true;
	}
	return resp;
}

public double calculaPromPorMes(int mes) {
	double promedio=-1;
	double suma=0;
	if(mes>0 && mes<=MESES) {
		mes--;
		for(int dep=0;dep<DPTOS; dep++) 
			suma+=ventas[mes][dep];
		promedio=suma/DPTOS;
	}
	return promedio;
}

public double promVentAnioDept(int dept) {
	double sum=0;
	
	if(dept>0&&dept<5) {
		dept--;
	for(int i=0;i<MESES;i++) {
		sum+=ventas[i][dept];
	}
	}
	return sum/MESES;
}

public String mesSinVenta() {
int mes=0,dep=0;
final String[]MONTHS= {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
boolean flag=false;
	while (mes<12 && !flag) {
		while(dep<4&&ventas[mes][dep]==0) 
			dep++;	
	if(dep>3)
		flag=true;
	else 
		mes++;
	}
	if(flag)
		return MONTHS[mes];
	else 
		return null;
}
public static void main (String[]args) {
	Cia a=new Cia();
	a.altaDatos(1,3,200);
	a.altaDatos(1,1,300);
	a.altaDatos(1,4,500);
	a.altaDatos(5,3,450);
	a.altaDatos(6,2,495);
	a.altaDatos(7,1,454);
	a.altaDatos(8,1,20);
	a.altaDatos(9,4,400);
System.out.println(a.mesSinVenta());
System.out.println(a.calculaPromPorMes(1));
System.out.println(a.promVentAnioDept(1));
}//main
}//class

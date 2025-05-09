package Interface;
/*
 * Gerardo Moguel
 * Metodos realizados con un arreglo de figuras geometricas.
 */
public class MetodosConFG {
private FigGeometrica[]arr;
private final int MAX=50;
private int total;

public MetodosConFG() {
	arr=new FigGeometrica[MAX];
	total=0;
}

public boolean altaFigGeom(double lado1,double lado2) {
	boolean resp=false;
	if(total<MAX) {
		Rectangulo x=new Rectangulo(lado1, lado2);
		arr[total]=x;
		total++;
		resp=true;
	}
	return resp;
}
public boolean altaFigGeom(double dato,char c) {
	boolean resp=false;
	if(total<MAX) {
		if(c=='X'||c=='x') {
			Cuadrado x=new Cuadrado(dato);
			arr[total]=x;
			total++;
			resp=true;
		}
		else if(c=='O'||c=='o') {
			Circulo x=new Circulo(dato);
			arr[total]=x;
			total++;
			resp=true;
		}
	}
	return resp;
}
public boolean altaFigGeom(double base, double lado1, double lado2) {
	boolean resp=false;
	if(total<MAX) {
		Triangulo x=new Triangulo(base,lado1,lado2);
		arr[total]=x;
		total++;
		resp=true;
	}
	return resp;
}

public String areaFigs() {
	StringBuilder sb= new StringBuilder();
	for(int i=0;i<total;i++) {
		sb.append("Area del "+arr[i].getClass().getSimpleName()+": ").append(arr[i].calculaArea()).append("\n");
	}
	return sb.toString();
}

public String biggestC() {
	double max=-1;
	String resp=null;
	for(int i=0;i<total;i++) {
		if(arr[i] instanceof Circulo) {
			if(((Circulo)arr[i]).getRadio()>max){
				max=((Circulo)arr[i]).getRadio();
				resp=(arr[i].toString());
			}
		}
	}
	return resp;
}

public int totalFigGeo(String tipo) {
	int x=0;
	for (int i=0;i<total;i++) {
		if(arr[i].getClass().getSimpleName().equals(tipo)) {
			x++;
		}
	}
	return x;
}

public int elimEquilat() {
	int x=0;
	int i=0;
	while(i<total) {
		if(arr[i] instanceof Triangulo && ((Triangulo)arr[i]).getBase()==((Triangulo)arr[i]).getLado1() && ((Triangulo)arr[i]).getBase()==((Triangulo)arr[i]).getLado2()) {
				arr[i]=arr[total-1];
				x++;
				total--;
			}
		else
			i++;
	}
	return x;
}

public static void main(String[]args) {
	MetodosConFG a=new MetodosConFG();
	a.altaFigGeom(2,'o');
	a.altaFigGeom(7,'o');
	a.altaFigGeom(1,'o');
	String x;
	x=a.biggestC();
	System.out.println(x);
	
	System.out.println("\nTotal de circulos: "+a.totalFigGeo("Circulo"));
	a.altaFigGeom(1, 2, 2);
	a.altaFigGeom(2, 2, 2);
	a.altaFigGeom(5, 5, 5);
	a.altaFigGeom(3, 2, 3);
	a.altaFigGeom(1, 2, 3);


	int z;
	z=a.totalFigGeo("Triangulo");
	System.out.println("\nPrevio eliminar equilateros: "+z);
	a.elimEquilat();
	z=a.totalFigGeo("Triangulo");
	System.out.println("Tras eliminar equilateros: "+z);
	System.out.println(a.areaFigs());
}
}

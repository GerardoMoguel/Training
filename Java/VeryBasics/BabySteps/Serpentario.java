/*
 * Gerardo M
 * Clase serpentario
 */
public class Serpentario {
private Serpiente[][] serp;
private int []totales;
private final int MAX_TIPO=20;
private final int MAX_CANT=30;
//otros atributos etc
public Serpentario() {
	serp=new Serpiente[MAX_TIPO][MAX_CANT];
	totales=new int[MAX_TIPO];
	for (int i=0;i<MAX_TIPO;i++) {
		totales[i]=0;
		for(int j=0;j<MAX_CANT;j++)
			serp[i][j]=null;
	}
}
public boolean altaSerpiente(int clave, int tipo, String alim) {
	boolean resp=false;
	int i=0;
	if(tipo>=0 && tipo<=MAX_TIPO&&totales[tipo]<MAX_CANT) {
		while(serp[tipo][i]!=null) 
			i++;
		
		serp[tipo][i]=new Serpiente(clave,tipo,alim);
		totales[tipo]++;
		resp=true;
	}
	return resp;
}




private int []buscaPosicion(Serpiente s){
int[]res= new int[2];
boolean flag=false;
int i,j,cont;
i=0;
while(i<MAX_TIPO && !flag) {
	if(totales[i]>0) {
		j=0;
		cont=0;
	while(j<MAX_CANT&&!flag && cont<totales[i]){
		if(serp[i][j]!=null) {
			if(serp.equals(s)) {
				flag=true;
				res[0]=i;
				res[1]=j;
			}
			cont++;
		}
		j++;
	}
}
	i++;
}

if(flag==false)
	res=null;
return res;
}

public String consultaPClave(int clave) {
	String resultado;
	int[]pos=this.buscaPosicion(new Serpiente(clave));
	if(pos==null)
		resultado=null;
	else
		resultado=serp[pos[0]][pos[1]].toString();
	return resultado;
}

public Serpiente bajaSerpiente(int clave) {
	Serpiente res;
	int[]pos=this.buscaPosicion(new Serpiente(clave));
	if(pos==null)
		res=null;
	else {
		res=serp[pos[0]][pos[1]];
		serp[pos[0]][pos[1]]=null;
		totales[pos[0]]--;
	}
	return res;
}

public int cuentaporAlimentacion(String alimento) {
	int contAlim,contAux,i,j;
	contAlim=0;
	for(i=0;i<MAX_TIPO;i++) {
		contAux=0;
		j=0;
		while(contAux<totales[i]) {
			if(serp[i][j]!=null) {
				contAux++;
				if((serp[i][j].getAlim().equalsIgnoreCase(alimento)));
				contAux++;
			}
			j++;
		}
		i++;
	}
	return contAlim;
	
}

public static void main(String[]args) {
	String res;
	Serpiente eliminada=new Serpiente(123);
	Serpentario uno=new Serpentario();
	uno.altaSerpiente(123, 2, "ratas");
	uno.altaSerpiente(433, 0, "insectos");
	uno.altaSerpiente(143, 3, "aves");
	uno.altaSerpiente(796, 3, "aves");
	uno.altaSerpiente(573, 0, "insectos");
	uno.altaSerpiente(125, 2, "ratas");
	uno.altaSerpiente(887, 2, "ratas");
	
	//prueba metodo consulta
	res=uno.consultaPClave(123);
if(res!=null)
	System.out.println("\nDatos de la serpiente: "+res);
else
	System.out.println("Serpiente no encontrada");
//prueba metodo baja
eliminada=uno.bajaSerpiente(123);
if(eliminada!=null)
	System.out.println("Eliminada "+eliminada);
else
	System.out.println("No encontrada");
	
}
}


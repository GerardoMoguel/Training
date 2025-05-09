
public class Zoologico {
	private String nombre;
	private static Animal [] arrAnimales;
	private static int cantidadDeDatosQueTieneDentroElArreglo;
	private static int MAX=100; //maximo que le vamos a asignar al arregl y lo escribo como atributo para despues
	//poder modificarlo en caso de que el usuario quiera
	
	public Zoologico (String nombre) { //constructor 1
		arrAnimales = new Animal[MAX];
		cantidadDeDatosQueTieneDentroElArreglo=0;
	}
	
	
	public Zoologico (String nombre, int MAX) { //constructor 2
		arrAnimales = new Animal[MAX];
		cantidadDeDatosQueTieneDentroElArreglo=0;
	}
	
	public int indicesArreglo() {
		return arrAnimales.length;
	}
	
	public static void recorreIzq(Animal[]arr,int total, int pos) {
		if(total<arr.length) 
			for(int i=pos;i<total;i++)
				arr[i]=arr[i+1];
	}
	
	public static boolean altaAnimal(String nombre, String dieta, int edad, String raza) {
		 boolean flag=false;
		if(cantidadDeDatosQueTieneDentroElArreglo<MAX) {
			Animal pene= new Animal(nombre, dieta, edad, raza);
			arrAnimales[cantidadDeDatosQueTieneDentroElArreglo]=pene;
		}
		cantidadDeDatosQueTieneDentroElArreglo++;
		return flag;
	}
	
	public static boolean bajaAnimal(String nombre) {
		int i=0;
		boolean flag=false;
		while(!arrAnimales[i].getNombre().equals(nombre) && i<cantidadDeDatosQueTieneDentroElArreglo) {
			i++;
		}
		
		if(arrAnimales[i].getNombre().equals(nombre)) {
			flag = true;
			recorreIzq(arrAnimales,cantidadDeDatosQueTieneDentroElArreglo, i);
			cantidadDeDatosQueTieneDentroElArreglo--;
		}
		return flag;
		
	}
	
	public static int cuantoAnimRaza(String raza) {
		int total=0;
		for (int i=0;i<cantidadDeDatosQueTieneDentroElArreglo;i++) {
			if(arrAnimales[i].getRaza().equals(raza)) {
				total++;
			}
		}
		return total;
	}
	
	public static double calculaPromedioEdades() {
		double suma=0.0;
		for(int i=0;i<cantidadDeDatosQueTieneDentroElArreglo;i++) {
			suma+=arrAnimales[i].getEdad();
		}
		return suma/cantidadDeDatosQueTieneDentroElArreglo;
	}
	
	public static int edadAnimRaza(String raza) {
		int []edades = new int [100];
		int total=0;
		for (int i=0;i<cantidadDeDatosQueTieneDentroElArreglo;i++) {
			if(arrAnimales[i].getRaza().equals(raza)) {
				edades[total]=arrAnimales[i].getEdad();
				total++;
			}
		}
		return total;
	}
	public static boolean determinaSiDieta(String dieta) {
		boolean flag=false;
		for (int i=0;i<cantidadDeDatosQueTieneDentroElArreglo;i++) {
			if(arrAnimales[i].getDieta().equals(dieta)) {
				flag=true;
			}
		}
		return flag;
	}
	
	
	/*public int cuantosRaza(Animal[]arr,int tot, String raza) {//tot = total utilizado de indices en el arreglo Animales
		int total=0;
		for (int i=0;i<tot;i++) {
			
		}
	}
	*/
	
	//main
	public static void main (String []args) {
		Zoologico zoologico;
		zoologico=new Zoologico("Zoologico 1");
		int a=zoologico.indicesArreglo();
		System.out.println(a);
		zoologico.altaAnimal("juan","caernivoro", 20, "negro");
	}
}

package Colas;

import javax.swing.RepaintManager;

public class Refugio{
	private ColaA<Animal> cola;
	
	public Refugio() {
		super();
		cola=new ColaA();
	}
	public Refugio(int MAX) {
		super();
		cola=new ColaA(MAX);
	}
	
	public Gato agregaGato(String color, char sexo) {
		Gato gato=new Gato(color,sexo);
		return gato;
	}
	
	public Perro agregaPerro(String color, char sexo) {
		Perro perro=new Perro(color,sexo);
		return perro;
	}
	
	public ColaA getCola() {
		return cola;
	}
	
	public String quitaAnimal(char gop) {
		String str=null;
		ColaA resp=new ColaA();
		Animal aux;
		
		switch (gop){
		case 'P':
		case 'p': 	{
			
			while(!cola.estaVacia()&& str==null) {
				aux=cola.quita();
				if(aux instanceof Perro) {
					str=aux.toString();
				}
				else {
					resp.agrega(aux);
				}
			}
			if(!cola.estaVacia()) {
				while(!cola.estaVacia()) {
					resp.agrega(cola.quita());
				}
			}
			cola=resp;
		}
		break;
		
		case 'G':
		case 'g':{
			while(!cola.estaVacia()&& str==null) {
				aux=cola.quita();
				if(aux instanceof Gato) {
					str=aux.toString();
				}
				else {
					resp.agrega(aux);
				}
			}
			if(!cola.estaVacia()) {
				while(!cola.estaVacia()) {
					resp.agrega(cola.quita());
				}
			}
			cola=resp;
		}
		break;
		
		default:{
			str=cola.quita().toString();
		}
		}
		return str;
	}
	
	public boolean agregaAnimal(char gop, String color, char sexo) {	//gop = gato o perro
		boolean resp;
		switch(gop){
		case 'G':{
			cola.agrega(agregaGato(color,sexo));
			resp=true;
		}
		break;
		case'g':{
			cola.agrega(agregaGato(color,sexo));
			resp=true;
		}
		break;
		case'P':{
			cola.agrega(agregaPerro(color,sexo));
			resp=true;
		}
		break;
		case'p':{
			cola.agrega(agregaPerro(color,sexo));
			resp=true;
		}
		break;
		default: {
			resp=false;
		}
		}
		return resp;
	}
		
	public String adopcion(char gop) {
		return quitaAnimal(gop);
	}
	
	public static void main(String[]args) {
		Refugio a=new Refugio();
		a.agregaAnimal('G', "cafe", 'M');
		a.agregaAnimal('P', "Negro", 'H');
		a.agregaAnimal('P', "Blanco", 'H');
		a.agregaAnimal('G', "Blanco y negro", 'M');
		
		System.out.println(a.getCola().getCola()[0]);
		System.out.println("\n"+a.getCola().getCola()[1]);
		System.out.println("\n"+a.getCola().getCola()[2]);
		System.out.println("\n"+a.getCola().getCola()[3]);

	
		System.out.println("\nAnimal adoptado: "+a.adopcion('P')+"\n");
		
		System.out.println(a.getCola().getCola()[0]);
		System.out.println("\n"+a.getCola().getCola()[1]);
		System.out.println("\n"+a.getCola().getCola()[2]);


	}
	
}

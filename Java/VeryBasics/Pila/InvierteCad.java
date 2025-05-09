package Pila;

public class InvierteCad {
private String x;
private String invertida;

public InvierteCad(String cadena) {
	this.x=cadena;
}

public String getInvertida() {
	return invertida;
}
public void setInvertida(String invertida) {
	this.invertida = invertida;
}
public String getX() {
	return x;
}

public void invierteCad() {
	StringBuilder sb=new StringBuilder();
	PilaA <Character>pila= new PilaA(50);
	for(int i=0;i<x.length();i++) {
		pila.push(x.charAt(i));
	}
	
	for(int j=0;j<x.length();j++) {
		sb.append(pila.peek());
		pila.pop();
	}
	setInvertida(sb.toString());
}
	
	public static void main (String []args) {
		InvierteCad a= new InvierteCad("Alegoria123");
		a.invierteCad();
		System.out.println(a.getX());
		System.out.println(a.getInvertida());
		
		InvierteCad b= new InvierteCad("");
		b.invierteCad();
		System.out.println(b.getX());
		System.out.println(b.getInvertida());
	}
}

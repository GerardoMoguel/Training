package Colas;

public class Perro extends Animal{
	
	
	public Perro(String color,char sexo) {
		super(color,sexo);
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Perro\n"+"Color: "+color+"\nSexo: "+sexo);
		return sb.toString();
	}
	
}

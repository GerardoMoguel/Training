package Colas;

public class Gato extends Animal{

	public Gato(String color,char sexo) {
		super(color,sexo);
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Gato\n"+"Color: "+color+"\nSexo: "+sexo);
		return sb.toString();
	}
}

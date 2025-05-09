
public class Animal {
	private String nombre;
	private String dieta;
	private int edad;
	private String raza;
	
	public Animal(String nombre, String dieta, int edad, String raza) {
		this.nombre = nombre;
		this.dieta = dieta;
		this.edad = edad;
		this.raza = raza;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDieta() {
		return dieta;
	}
	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	
	
}

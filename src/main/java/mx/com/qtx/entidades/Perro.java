package mx.com.qtx.entidades;

public class Perro {
	private int id;
	private String raza;
	private int edad;
	private String nombre;
	
	public Perro() {
		super();
	}

	public Perro(int id, String raza, int edad, String nombre) {
		super();
		this.id = id;
		this.raza = raza;
		this.edad = edad;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Perro [id=" + id + ", raza=" + raza + ", edad=" + edad + ", nombre=" + nombre + "]";
	}
}

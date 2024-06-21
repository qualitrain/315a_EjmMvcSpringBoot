package mx.com.qtx.servicios;

import java.util.List;

import mx.com.qtx.entidades.Perro;

public interface IRepositorioPerros {
	public Perro getPerroXID(int id);
	public int insertarPerro(Perro perro);
	public List<Perro> getPerros();
	public Perro getPerroXNombre(String nombre);
}

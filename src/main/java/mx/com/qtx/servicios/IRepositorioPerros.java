package mx.com.qtx.servicios;

import java.util.List;

import mx.com.qtx.entidades.Perro;

public interface IRepositorioPerros {
	Perro getPerroXID(int id);
	int insertarPerro(Perro perro);
	List<Perro> getPerros();
	Perro getPerroXNombre(String nombre);
}

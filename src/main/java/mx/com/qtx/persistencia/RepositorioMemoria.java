package mx.com.qtx.persistencia;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import mx.com.qtx.entidades.Perro;
import mx.com.qtx.servicios.IRepositorioPerros;

@Repository
public class RepositorioMemoria implements IRepositorioPerros {
	private Map<Integer,Perro> perros;
	private Map<String, Perro> perrosXnombre;
	
	public RepositorioMemoria() {
		super();
		this.perros = new Hashtable<>();
		this.perrosXnombre = new Hashtable<>();
	}

	@Override
	public Perro getPerroXID(int id) {
		return this.perros.get(id);
	}

	@Override
	public int insertarPerro(Perro perro) {
		int numPerro = 1;
		if(this.perros.isEmpty() == false) {
			numPerro = this.getUltId();
			numPerro++;
		}
		if(this.getPerroXNombre(perro.getNombre())!= null) {
			return 0;
		}
		perro.setId(numPerro);
		this.perros.put(numPerro, perro);
		this.perrosXnombre.put(perro.getNombre(), perro);
		return 1;
	}

	@Override
	public List<Perro> getPerros() {
		return new ArrayList<Perro>(this.perros.values());
	}
	
	private int getUltId() {
		if(this.perros.isEmpty()) 
			return 0;
		return this.perros.keySet()
					      .stream()
					      .sorted((k1,k2)-> k1 < k2 ? 1 : -1)
					      .findFirst()
					      .get();
	}

	@Override
	public Perro getPerroXNombre(String nombre) {
		String nombrePerro = nombre == null ? null :nombre.trim();
		return this.perrosXnombre.get(nombrePerro);
	}

}

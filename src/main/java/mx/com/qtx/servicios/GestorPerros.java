package mx.com.qtx.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import mx.com.qtx.entidades.Perro;
import mx.com.qtx.persistencia.RepositorioMemoria;

@Service
public class GestorPerros {
    private IRepositorioPerros repositorio;

	public GestorPerros() {
		super();
		this.repositorio = new RepositorioMemoria();
	}
	
	public List<ErrorValidacion> validarPerroInsercion(Perro perro){
		List<ErrorValidacion> errores = new ArrayList<>();
		if(perro.getNombre() == null || perro.getNombre().trim().isEmpty()) {
			errores.add(new ErrorValidacion("nombre","no tiene valor y es obligatorio"));
		}
		if(perro.getRaza() == null || perro.getRaza().trim().isEmpty()) {
			errores.add(new ErrorValidacion("raza","no tiene valor y es obligatorio"));
		}
		if(perro.getEdad() < 0 ) {
			errores.add(new ErrorValidacion("edad","debe ser un valor >= 0"));
		}
		Perro perroDuplicado = this.repositorio.getPerroXNombre(perro.getNombre());
		if(perroDuplicado != null) {
			errores.add(new ErrorValidacion("nombre","ya existe un perro con ese nombre:" 
						+ perroDuplicado.toString()));
		}
		return errores;
	}
	public List<ErrorValidacion> validarPerroInsercion(Map<String,String> mapPerro){
		List<ErrorValidacion> errores = new ArrayList<>();
		String nombre = mapPerro.get("nombre");
		if(nombre == null || nombre.trim().isEmpty()) {
			errores.add(new ErrorValidacion("nombre","no tiene valor y es obligatorio"));
		}
		String raza = mapPerro.get("raza");
		if(raza == null || raza.trim().isEmpty()) {
			errores.add(new ErrorValidacion("raza","no tiene valor y es obligatorio"));
		}
	
		String strEdad = mapPerro.get("edad");
		if(strEdad == null || strEdad.trim().isEmpty()) {
			errores.add(new ErrorValidacion("edad","es un valor obligatorio"));
		}
		else
		if(esNumero(strEdad) == false) {
			errores.add(new ErrorValidacion("edad","debe ser un n�mero entero y positivo"));
		}
		else
		if(Integer.parseInt(strEdad) < 0 ) {
			errores.add(new ErrorValidacion("edad","debe ser un valor >= 0"));
		}
		if(errores.size() >0)
			return errores;
		Perro perro = crearPerroIns(mapPerro);
		Perro perroDuplicado = this.repositorio.getPerroXNombre(perro.getNombre());
		if(perroDuplicado != null) {
			errores.add(new ErrorValidacion("nombre","ya existe un perro con ese nombre:" 
						+ perroDuplicado.toString()));
		}
		return errores;
	}
	public Perro crearPerroIns(Map<String, String> mapPerro) {
		Perro perro = new Perro();
		perro.setEdad(Integer.parseInt(mapPerro.get("edad")));
		perro.setNombre(mapPerro.get("nombre"));
		perro.setRaza(mapPerro.get("raza"));
		return perro;
	}
	private boolean esNumero(String strEdad) {
		try {
			Integer.parseInt(strEdad);
			return true;
		}
		catch (NumberFormatException nfex) {
			return false;
		}
	}
	
	public List<ErrorValidacion> validarParametros(HttpServletRequest request){
		List<ErrorValidacion> errores = new ArrayList<>();
		String strEdad = request.getParameter("edad");
		if(strEdad == null) {
			errores.add(new ErrorValidacion("edad","es un valor obligatorio"));
		}
		else
		if(esNumero(strEdad) == false) {
			errores.add(new ErrorValidacion("edad","debe ser un n�mero entero y positivo"));
		}
		
		return errores;
	}

	public void insertarPerro(Perro perro) {
		List<ErrorValidacion> errores = this.validarPerroInsercion(perro);
		if(errores.size() == 0) {
			if( this.repositorio.insertarPerro(perro) > 0)
				return;
			else
				perro.setId(-1);
		}
		perro.setId(errores.size() * -1);
	}
	public List<Perro> getPerrosTodos(){
		return this.repositorio.getPerros();
	}
}

package mx.com.qtx.servicios;

import java.util.ArrayList;
import java.util.List;

public class ErrorValidacion {
	private String campo;
	private String descripcion;
	public ErrorValidacion(String campo, String descripcion) {
		super();
		this.campo = campo;
		this.descripcion = descripcion;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "ErrorValidacion [campo=" + campo + ", descripcion=" + descripcion + "]";
	}
	public static List<String> getMensajesErrorPorCampo(String campo, 
										List<ErrorValidacion> listaErrores){
		List<String> mensajes = new ArrayList<>();
		for(ErrorValidacion errorI : listaErrores) {
			if(errorI.campo.equalsIgnoreCase(campo))
				mensajes.add(errorI.getDescripcion());
		}
		return mensajes;
	}
}

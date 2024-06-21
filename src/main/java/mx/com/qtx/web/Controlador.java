package mx.com.qtx.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServletRequest;
import mx.com.qtx.entidades.Perro;
import mx.com.qtx.servicios.ErrorValidacion;
import mx.com.qtx.servicios.GestorPerros;

@Controller
public class Controlador {
	
	private static Logger bitacora = LoggerFactory.getLogger(Controlador.class);
	
	@Autowired
	private GestorPerros gp;
		
	public Controlador() {
		bitacora.info("Controlador instanciado") ;
	}
	
	@GetMapping({"/","regresar"})
	public String atenderRaiz() {
		bitacora.info("atenderRaiz") ;
		return "menu";
	}
	
	@GetMapping("/AltaPerro")
	public String enrutarAInsercionPerro() {
		bitacora.info("enrutarAInsercionPerro: ");
		return "Formulario";
	}

	@PostMapping("/AltaPerro")
	public String atenderInsercionPerro(@RequestParam Map<String,String> paramsPerro,
			Model mapModel) {
		bitacora.info("atenderInsercionPerro: " + paramsPerro);
		List<ErrorValidacion> listErr = gp.validarPerroInsercion(paramsPerro);
		if(listErr.size() == 0) {
			  Perro perroNvo = gp.crearPerroIns(paramsPerro);
			  gp.insertarPerro(perroNvo);
			  mapModel.addAttribute("mensaje", "Perro dado de alta: " + perroNvo);
			  return "Formulario";
		}
		mapModel.addAttribute("errores", listErr);
		mapModel.addAttribute("params", paramsPerro);
		mapModel.addAttribute("mensaje", "Formulario con errores (" + listErr.size() + ")");
		return "Formulario";
	}
	
	@GetMapping("/ConsultaPerros")
	public String enrutarAconsultaPerros(Model mapModel) {
		bitacora.info("enrutarAconsultaPerros: " );
	  	List<Perro> listPerros = this.gp.getPerrosTodos();
	  	mapModel.addAttribute("perros", listPerros);		
		return "ConsultaPerros";
	}
	
	@GetMapping("/monitor")	
	public String monitorear(HttpServletRequest req) {
		bitacora.info("monitorear");
		bitacora.info("verbo http:" + req.getMethod());
		ServletContext contexto = req.getServletContext();
		Map<String, ? extends ServletRegistration> registrosServlet = contexto.getServletRegistrations();
		bitacora.info("Servlets registrados");
		for(String servletI: registrosServlet.keySet()) {
			ServletRegistration registroI = registrosServlet.get(servletI);
			bitacora.info(servletI + ":" + registroI.getMappings() + ", " + registroI.getClassName());
		}
		
		bitacora.info("atributo modo en contexto:" + contexto.getAttribute("modo"));
		
		return "menu";
	}
}

package mx.com.qtx.web;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@Component
@WebListener
public class EscuchaContexto implements ServletContextListener{
	
	private static Logger bitacora = LoggerFactory.getLogger(EscuchaContexto.class);
	
	public EscuchaContexto() {
		bitacora.info("EscuchaContexto instanciado");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		bitacora.info("contextInitialized");
		ServletContext contextoApp = sce.getServletContext();
		bitacora.info("contextoApp:" + contextoApp);
		contextoApp.setAttribute("modo", this.getModoDebugJsp(contextoApp));
		bitacora.info("modo:" + contextoApp.getAttribute("modo"));
	}
	
	private String getModoDebugJsp(ServletContext sc) {
        // Cargamos el archivo de propiedades
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try (InputStream recurso = loader.getResourceAsStream("application.properties")){
            prop.load(recurso);
        } catch (Exception e) {
            bitacora.error("No fue posible leer archivo de propiedades");
            bitacora.error(e.getClass().getName() + ":" + e.getMessage());
            return "error";
        }

        // Leemos la propiedad que nos interesa
        return prop.getProperty("qtx.modoJsp");

	}
	
}

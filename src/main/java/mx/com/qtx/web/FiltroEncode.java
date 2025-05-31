package mx.com.qtx.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

@Component
@WebFilter("/AltaPerro")
public class FiltroEncode extends HttpFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger bitacora = LoggerFactory.getLogger(FiltroEncode.class);
	public FiltroEncode() {
        super();
		bitacora.info("FiltroEncode instanciado");
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		bitacora.info("req.contentType:"+ request.getContentType());
		String contentTypeReq = request.getContentType();
		if(contentTypeReq != null && contentTypeReq.equalsIgnoreCase(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
			request.setCharacterEncoding("ISO-8859-1");
		}
		chain.doFilter(request, response);
		bitacora.info("resp.contentType:"+ response.getContentType());
	}
}

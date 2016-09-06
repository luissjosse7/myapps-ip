package ip;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class IpBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Dato> headers;

	@PostConstruct
	public void init() {
		cargarIp();
	}

	private void cargarIp() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		headers = new ArrayList<>();
		headers.add(new Dato("<b><u>REMOTE ADDRESS JAVA</u></b>", request.getRemoteAddr()));

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();

			System.out.println(name + " : " + request.getHeader(name));
			headers.add(new Dato(name, request.getHeader(name)));
		}

		String cad = "";
		URL location = IpBean.class.getProtectionDomain().getCodeSource().getLocation();
		System.out.println("absolutePath: " + location.getPath());

		cad = location.getPath();

		File classes = new File(location.getPath());
		// System.out.println("f: " + classes.getAbsolutePath());
		// System.out.println("f parent: " + classes.getParent());

		cad = cad + " | " + classes.getAbsolutePath();

		File webinf = new File(classes.getParent());

		cad = cad + " | " + webinf.getAbsolutePath();

		File vistaccc = new File(webinf.getParent());
		cad = cad + " | " + vistaccc.getAbsolutePath();
		File deployments = new File(vistaccc.getParent());
		cad = cad + " | " + deployments.getAbsolutePath();
		File standalone = new File(deployments.getParent());
		cad = cad + " | " + standalone.getAbsolutePath();

		System.out.println("standalone dir: " + standalone.getAbsolutePath());

		headers.add(new Dato("URL STANDALONE", standalone.getAbsolutePath()));
	}

	public List<Dato> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Dato> headers) {
		//devuelve la cabecera
		this.headers = headers;
	}
	
	public void prueba(){
		
	}
	
	public void Zulema(){
		System.out.println("Modificado por Zulema pruebas GIT"); // comentario adicionado
	}

	public void testLuis(){
		// test luis
	}
}

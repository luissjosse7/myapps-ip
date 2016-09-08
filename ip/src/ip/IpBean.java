package ip;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class IpBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(IpBean.class);

	private String ipClient;
	private String ipClientJs;
	private String urlServlet;
	private String protocoloIp;

	private List<Dato> headers;

	@PostConstruct
	public void init() {
		cargarIp();
	}

	private void cargarIp() {
		try {

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

			headers = new ArrayList<>();
			headers.add(new Dato("<b><u>REMOTE ADDRESS JAVA</u></b>", request.getRemoteAddr()));

			headers.add(new Dato("ClientIP:", request.getRemoteAddr()));

			headers.add(new Dato("LocalPort:", request.getLocalPort() + ""));
			// headers.add(new Dato("RemotePort:", request.getRemotePort() + ""));
			// headers.add(new Dato("ServerPort:", request.getServerPort() + ""));
			headers.add(new Dato("ServerIP:", InetAddress.getLocalHost().getHostAddress()));
			headers.add(new Dato("DOMAIN: ", request.getServerName()));
			headers.add(new Dato(".", ""));

			String scheme = "http";
			if (request.getScheme() != null && request.getScheme().toLowerCase().trim().equals("https")) {
				scheme = "https";
			}
			// ipClient = request.getRemoteAddr();
			// protocoloIp = scheme + "://" + InetAddress.getLocalHost().getHostAddress();
			protocoloIp = scheme + "://" + request.getServerName();
			// String url = scheme + "://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort() + "/ip/MyServlet";
			String url = scheme + "://" + request.getServerName() + ":" + request.getLocalPort() + "/ip/MyServlet";
			log.info("url: " + url);
			headers.add(new Dato("url servlet: ", url));
			urlServlet = url;

			// ipClient = getIpClientServlet(url);

			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();

				System.out.println(name + " : " + request.getHeader(name));
				headers.add(new Dato(name, request.getHeader(name)));
			}

		} catch (Exception e) {
			log.error("Error al cargar ip: ", e);
		}
		// getIpAddressAndPort();
	}

	public String login() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			this.ipClientJs = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form_cuenta:ipServlet");
			log.info("ipClientJs: " + ipClientJs);

			request.getSession().setAttribute("TEMP$IP_CLIENT", ipClientJs);
			return "menu.xhtml";
		} catch (Exception e) {
			log.error("Error en el login: ", e);
		}
		return "";
	}

	// public void preLogin() {
	// try {
	// ipClient = getIpClientServlet(urlServlet);
	// HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	// request.getSession().setAttribute("TEMP$IP_CLIENT", ipClient);
	// } catch (Exception e) {
	// log.error("Error en el preLogin: ", e);
	// }
	// }

	private String getIpClientServlet(String url) {
		try {
			// http://www.yahoo.com/
			URL page = new URL(url);
			URLConnection yc = page.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = in.readLine();
			log.info("ipClient del servlet: " + inputLine);

			// while ((inputLine = in.readLine()) != null)
			// System.out.println(inputLine);
			in.close();

			return inputLine;
		} catch (Exception e) {
			log.error("Error al obtener ip del servlet: ", e);
		}

		return "";
	}

	public List<Dato> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Dato> headers) {
		// devuelve la cabecera
		this.headers = headers;
	}

	public void prueba() {

	}

	public void Zulema() {
		System.out.println("Modificado por Zulema pruebas GIT"); // comentario adicionado
	}

	public void testLuis() {
		// test luis
	}

	public String getIpClient() {
		return ipClient;
	}

	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	public String getUrlServlet() {
		return urlServlet;
	}

	public void setUrlServlet(String urlServlet) {
		this.urlServlet = urlServlet;
	}

	public String getIpClientJs() {
		return ipClientJs;
	}

	public void setIpClientJs(String ipClientJs) {
		this.ipClientJs = ipClientJs;
	}

	public String getProtocoloIp() {
		return protocoloIp;
	}

	public void setProtocoloIp(String protocoloIp) {
		this.protocoloIp = protocoloIp;
	}
}

package ip;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger(MenuBean.class);

	private String ipClient;

	@PostConstruct
	public void init() {
		iniciar();
	}

	public void iniciar() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			this.ipClient = (String) request.getSession().getAttribute("TEMP$IP_CLIENT");
			// this.ipClient = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipServlet");
		} catch (Exception e) {
			log.error("Error al iniciar: ", e);
		}
	}

	public String getIpClient() {
		return ipClient;
	}

	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}
}

package exemplos.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloBean")
@SessionScoped
public class HelloBean {
	
	private String seuSite;

	public String getSeuSite() {
		return seuSite;
	}

	public void setSeuSite(String seuSite) {
		this.seuSite = seuSite;
	}
}
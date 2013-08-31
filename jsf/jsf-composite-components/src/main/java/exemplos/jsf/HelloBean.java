package exemplos.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import exemplos.jsf.model.Endereco;

@ManagedBean(name = "helloBean")
@SessionScoped
public class HelloBean {
	
	private Endereco residencial;
	private Endereco trabalho;	
	private Endereco entrega;
	
	public HelloBean() {
		this.residencial = new Endereco();
		this.trabalho = new Endereco();
		this.entrega = new Endereco();
	}
	
	public Endereco getResidencial() {
		return residencial;
	}
	
	public void setResidencial(Endereco residencial) {
		this.residencial = residencial;
	}
	
	public Endereco getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Endereco trabalho) {
		this.trabalho = trabalho;
	}

	public Endereco getEntrega() {
		return entrega;
	}

	public void setEntrega(Endereco entrega) {
		this.entrega = entrega;
	}
}
package exemplos.jsf.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.model.SelectItem;

import exemplos.jsf.model.TipoLogradouro;
import exemplos.jsf.model.UF;

@FacesComponent("enderecoComponent")
public class EnderecoComponent extends UINamingContainer implements Serializable{

	/*											Constantes
	 * ------------------------------------------------------------------------
	 */
	
	private static final long serialVersionUID = -8872487362117877631L;
	
	/*											Atributos
	 * ------------------------------------------------------------------------
	 */
	
	private Collection<SelectItem> tiposLogradouro = new ArrayList<SelectItem>();
	private Collection<SelectItem> ufs = new ArrayList<SelectItem>();

	/*											Construtores
	 * ------------------------------------------------------------------------
	 */
	
	public EnderecoComponent(){
		preencherUfs();
		preencherTiposLogradouro();
	}

	private void preencherUfs(){
		this.ufs.clear();
		
		this.ufs.add(new SelectItem(null, "Selecione"));
		for(UF uf : UF.values()){
			this.ufs.add(new SelectItem(uf, uf.getNome(), uf.getDescricao()));
		}
	}
	
	private void preencherTiposLogradouro(){
		this.tiposLogradouro.clear();
		
		this.tiposLogradouro.add(new SelectItem(null, "Selecione"));
		for(TipoLogradouro tl: TipoLogradouro.values()){
			this.tiposLogradouro.add(new SelectItem(tl, tl.getNome(), tl.getDescricao()));
		}
	}
	
	public Collection<SelectItem> getUfs(){
		return ufs;
	}
	
	public Collection<SelectItem> getTiposLogradouro(){
		return tiposLogradouro;
	}
}
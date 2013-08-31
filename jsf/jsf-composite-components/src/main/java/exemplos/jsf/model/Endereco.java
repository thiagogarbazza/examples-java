package exemplos.jsf.model;

public class Endereco {
	
	private String cep;
	private TipoLogradouro tipoLogradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private UF uf;
	
	public Endereco() {}
	
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		
		if(this.cep!= null && this.cep.isEmpty() ==false){
			toString.append(this.cep);
		}
		
		if(this.tipoLogradouro!= null){
			toString.append(", ").append(this.tipoLogradouro);
		}
		
		if(this.logradouro!= null && this.logradouro.isEmpty() ==false){
			toString.append(", ").append(this.logradouro);
		}
		
		if(this.numero!= null && this.numero.isEmpty() ==false){
			toString.append(" ").append(this.numero);
		}
		
		if(this.complemento!= null && this.complemento.isEmpty() ==false){
			toString.append("(").append(this.complemento).append(")");
		}
		
		if(this.bairro!= null && this.bairro.isEmpty() ==false){
			toString.append(" - ").append(this.bairro);
		}
		
		if(this.cidade!= null && this.cidade.isEmpty() ==false){
			toString.append(" - ").append(this.cidade);
		}
		
		if(this.uf!= null){
			toString.append(" - ").append(this.uf);
		}
		
		return toString.toString();
	}
	
	
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
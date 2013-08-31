package exemplos.jsf.model;

public enum UF{

	AC("AC", 12,"Acre", "Estado do Acre"),
	AL("AL", 27, "Alagoas", "Estado de Alagoas"),
	AP("AP", 16, "Amapá", "Estado do Amapá"),
	AM("AM", 13, "Amazonas", "Estado do Amazonas"),
	BA("BA", 29, "Bahia", "Estado da Bahia"),
	CE("CE", 23, "Ceará", "Estado do Ceará"),
	DF("DF", 53, "Distrito Federal","Distrito Federal"),
	ES("ES", 32, "Espírito Santo", "Estado do Espírito Santo"),
	GO("GO", 52, "Goiás", "Estado de Goiás"),
	MA("MA", 21, "Maranhão", "Estado do Maranhão"),
	MT("MT", 51, "Mato Grosso", "Estado de Mato Grosso"),
	MS("MS", 50, "Mato Grosso Do Sul", "Estado do Mato Grosso De Sul"),
	MG("MG", 31, "Minas Gerais", "Estado de Minas Gerais"),
	PA("PA", 15, "Pará", "Estado do Pará"),
	PB("PB", 25, "Paraíba",  "Estado da Paraíba"),
	PR("PR", 41, "Paraná", "Estado do Paraná"),
	PE("PE", 26, "Pernambuco", "Estado de Pernambuco"),
	PI("PI", 22, "Piauí", "Estado do Piauí"),
	RJ("RJ", 33, "Rio De Janeiro", "Estado do Rio De Janeiro"),
	RN("RN", 24, "Rio Grande Do Norte", "Estado do Rio Grande Do Norte"),
	RS("RS", 43, "Rio Grande Do Sul", "Estado do Rio Grande Do Sul"),
	RO("RO", 11, "Rondônia", "Estado de Rondônia"),
	RR("RR", 14, "Roraima", "Estado de Roraima"),
	SC("SC", 42, "Santa Catarina", "Estado de Santa Catarina"),
	SP("SP", 35, "São Paulo", "Estado de São Paulo"),
	SE("SE", 28, "Sergipe", "Estado de Sergipe"),
	TO("TO", 17, "Tocantins", "Estado de Tocantins");

	private final String sigla;
	private final int codigo;
	private final String nome;
	private final String descricao;
	
	private UF(String sigla, int codigo, String nome, String descricao){
		this.sigla = sigla;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getCodigo(){
		return codigo;
	}
	
	public final String getDescricao(){
		return descricao;
	}

	public String getNome(){
		return nome;
	}

	public String getSigla(){
		return sigla;
	}

	@Override
	public String toString(){
		return this.sigla;
	}
}
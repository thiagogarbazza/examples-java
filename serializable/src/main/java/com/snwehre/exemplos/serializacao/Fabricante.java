package com.snwehre.exemplos.serializacao;

public class Fabricante {
	
	private Long id;
	private String nome;
	private Long cnpj;
	
	public Fabricante(String nome, Long cnpj) {
		super();
		this.id = System.currentTimeMillis();
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	@Override
	public String toString() {
		return this.id+", "+this.nome+", "+this.cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}
}
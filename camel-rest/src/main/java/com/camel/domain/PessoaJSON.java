package com.camel.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"nome","idade"})
public class PessoaJSON {
	
	@JsonProperty(value = "nome")
	private String nome;
	@JsonProperty(value = "idade")
	private int idade;
	
	public PessoaJSON () {
		
	}
	
	public PessoaJSON(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

}

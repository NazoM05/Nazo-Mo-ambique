package com.example.nazomocambique.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	
	private String nome;
	private String cor;
	private boolean status;
	private String tamanho;
	private float preco;
	private String tipo;
	private String imagem; // nome do arquivo em static/img (ex.: camisa-militar.svg)
	
	public Produto() {
	}

	public Produto(Long id, String nome, String cor, boolean status, String tamanho, float preco, String tipo,
			String imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.cor = cor;
		this.status = status;
		this.tamanho = tamanho;
		this.preco = preco;
		this.tipo = tipo;
		this.imagem = imagem;
	}
	

}



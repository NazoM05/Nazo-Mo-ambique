package com.example.nazomocambique.model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
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

	@Column(length = 50)
	private String cor; // nome da cor (ex.: "Rosa") ou código hexadecimal (ex.: "#e0577c")

	// true = ativo, false = inativo. Produto novo (ex.: tela de cadastro) já nasce ativo.
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status = true;

	private String tamanho;
	private float preco;
	private String tipo;
	private String imagem; // nome do arquivo em static/img (ex.: camisa-militar.svg)

	// Cores conhecidas (nome em minúsculas -> hexadecimal usado na amostra da tela de detalhes)
	private static final Map<String, String> CORES_CSS = Map.ofEntries(
			Map.entry("verde militar", "#6e7d47"),
			Map.entry("azul marinho", "#1f3a5f"),
			Map.entry("branco", "#ffffff"),
			Map.entry("rosa claro", "#f4d3dc"),
			Map.entry("preto", "#2b2b2b"),
			Map.entry("grafite", "#3a3a47"),
			Map.entry("cinza", "#8a9095"),
			Map.entry("cinza chumbo", "#797b81"),
			Map.entry("rosa", "#e58ca5"),
			Map.entry("rosa e branco", "#e0577c"),
			Map.entry("azul jeans", "#6e8eb9"),
			Map.entry("azul jeans escuro", "#3a4d67"),
			Map.entry("azul escuro", "#19283f"),
			Map.entry("verde petróleo", "#298c80"),
			Map.entry("caqui", "#8a7a52"),
			Map.entry("lilás", "#b79ad6"),
			Map.entry("pêssego", "#fbe3d6"),
			Map.entry("bege", "#e9d3b4"),
			Map.entry("caramelo", "#b4916d"),
			Map.entry("vermelho", "#b3202a"));

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

	/**
	 * Cor pronta para usar em CSS (#rrggbb) ou null quando a cor está vazia/desconhecida.
	 * Só devolve valores validados, então é seguro usar em th:style.
	 */
	@Transient
	public String getCorCss() {
		if (cor == null) {
			return null;
		}
		String chave = cor.trim().toLowerCase(Locale.ROOT);
		if (chave.matches("#([0-9a-f]{3}|[0-9a-f]{6})")) {
			return chave;
		}
		return CORES_CSS.get(chave);
	}

	/**
	 * Tamanhos disponíveis do produto. O campo "tamanho" guarda os tamanhos separados por vírgula
	 * (ex.: "P,M,G"); este método devolve a lista pronta para a tela.
	 */
	@Transient
	public List<String> getTamanhosDisponiveis() {
		if (tamanho == null || tamanho.isBlank()) {
			return List.of();
		}
		return Arrays.stream(tamanho.split(",")).map(String::trim).filter(t -> !t.isEmpty()).toList();
	}
}

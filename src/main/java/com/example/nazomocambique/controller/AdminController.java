package com.example.nazomocambique.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.nazomocambique.model.Produto;
import com.example.nazomocambique.service.ProdutoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	// Pasta onde as imagens enviadas são gravadas (servida em /img/** pelo WebConfig)
	private static final Path PASTA_IMAGENS = Paths.get("uploads/img");

	ProdutoService produtoService;

	public AdminController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping({"/produto/listar"})
	public String listar(Model model) {
		model.addAttribute("listaProdutos", produtoService.listarTodos());
		return "admin/produto/listar";
	}

	@GetMapping({"/produto/cadastrar"})
	public String cadastrar(Model model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("listaProdutos", produtoService.listarTodos());
		return "admin/produto/cadastrar";
	}

	@GetMapping({"/produto/editar/{id}"})
	public String editar(Model model, @PathVariable Long id) {
		try {
			model.addAttribute("produto", produtoService.buscarPorId(id));
			return "admin/produto/form";
		} catch (Exception e) {
			return "redirect:/admin/produto/listar?erro=naoEncontrado";
		}
	}

	// Usado pelo cadastrar (id nulo) e pelo editar (id preenchido)
	@PostMapping({"/produto/salvar"})
	public String salvar(Produto produto,
			@RequestParam(value = "arquivoImagem", required = false) MultipartFile arquivoImagem) {
		try {

			if (arquivoImagem != null && !arquivoImagem.isEmpty()) {
				produto.setImagem(gravarImagem(arquivoImagem));
			} else if (produto.getImagem() != null && produto.getImagem().isBlank()) {
				produto.setImagem(null); // campo oculto vazio vira "sem imagem"
			}

			produtoService.salvarProduto(produto);
			return "redirect:/admin/produto/listar?sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/produto/listar?erro=salvar";
		}
	}

	// Ativar / inativar (botões da listagem)
	@PostMapping({"/produto/status/{id}"})
	public String alterarStatus(@PathVariable Long id, @RequestParam boolean status) {
		try {
			Produto produto = produtoService.buscarPorId(id);
			produto.setStatus(status);
			produtoService.salvarProduto(produto);
			return "redirect:/admin/produto/listar?sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/produto/listar?erro=status";
		}
	}

	private String gravarImagem(MultipartFile arquivo) throws IOException {
		String original = arquivo.getOriginalFilename();
		String extensao = (original != null && original.contains("."))
				? original.substring(original.lastIndexOf('.')).toLowerCase() : "";
		String nome = UUID.randomUUID() + extensao;
		Files.createDirectories(PASTA_IMAGENS);
		Files.copy(arquivo.getInputStream(), PASTA_IMAGENS.resolve(nome));
		return nome;
	}
}

package com.example.nazomocambique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nazomocambique.model.Produto;
import com.example.nazomocambique.service.ProdutoService;

@Controller 
@RequestMapping("/admin")
public class AdminController {

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
		model.addAttribute("produto",  new  Produto());
		return "admin/produto/form";
	}
	
	@GetMapping({"/produto/editar/{id}"})
	public String editar(Model model, @PathVariable Long id) {
		
		try {
			model.addAttribute("produto",  produtoService.buscarPorId(id));
			return "admin/produto/form";

		} catch (Exception e) {
			return "redirect:/admin/produto/listar?erro=naoEncontrado";
		}
	
		}
	
	
	@GetMapping({"/produto/salvar"})
	public String salvar(Model model, Produto produto) {
		produtoService.salvarProduto(produto);
		return "redirect:/admin/produto/listar?sucesso";
	}
}

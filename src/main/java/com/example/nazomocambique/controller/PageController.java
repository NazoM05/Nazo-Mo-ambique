package com.example.nazomocambique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.nazomocambique.service.ProdutoService;

@Controller 
public class PageController {

	ProdutoService produtoService;
	public PageController (ProdutoService produtoService) {
		this.produtoService = produtoService;
	}


	@GetMapping({"/", "/home"})
	public String home(Model model) {
		model.addAttribute("listaProdutos", produtoService.listarTodos());
		return "home";
	}

	@GetMapping("/detalhes/{id}")
	public String detalhes(@PathVariable Long id, Model model) {
		model.addAttribute("produto", produtoService.buscarPorId(id));
		return "detalhes";
	}
}


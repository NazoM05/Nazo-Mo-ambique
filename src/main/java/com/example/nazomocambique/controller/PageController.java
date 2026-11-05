package com.example.nazomocambique.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nazomocambique.model.Produto;
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

	
	@GetMapping({"/pesquisa"})
    public String pesquisa(@RequestParam Optional<String> p, Model model) {
		if(p.isPresent()) {
			model.addAttribute("listaProdutos", produtoService.pesquisarPorNome(p.get()));
			return "home";
		}
        return "redirect:/home";
    }
}

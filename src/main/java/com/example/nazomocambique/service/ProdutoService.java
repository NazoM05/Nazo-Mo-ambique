package com.example.nazomocambique.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nazomocambique.model.Produto;
import com.example.nazomocambique.repository.ProdutoRepository;

@Service
public class ProdutoService {
   
    private ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository) {
    	this.produtoRepository = produtoRepository;
    }
    
    

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado."));
    }

    public Produto salvarProduto(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo");
        }
        return produtoRepository.save(produto);
    }
}

package com.fiap.spring.webflux.controller;

import com.fiap.spring.webflux.model.Produto;
import com.fiap.spring.webflux.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produto")
    public ResponseEntity<Flux<Produto>> getProdutos() {
        final var produtos = produtoService.buscarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Mono<Produto>> getProduto(@PathVariable Long id) {
        final var produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }
}

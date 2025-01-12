package com.fiap.spring.webflux.controller;

import com.fiap.spring.webflux.model.Produto;
import com.fiap.spring.webflux.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produto")
    public Flux<ResponseEntity<List<Produto>>> getProdutos() {
        final var produtos = produtoService.buscarTodos();
        return Flux.just(ResponseEntity.ok(produtos));
    }

    @GetMapping("/produto/{id}")
    public Mono<ResponseEntity<Produto>> getProduto(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(value -> Mono.just(ResponseEntity.ok(value)))
                .orElseGet(() -> Mono.just(ResponseEntity.notFound().build()));
    }
}

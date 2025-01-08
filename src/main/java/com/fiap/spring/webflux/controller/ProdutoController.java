package com.fiap.spring.webflux.controller;

import com.fiap.spring.webflux.model.Produto;
import com.fiap.spring.webflux.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {
        final var produto = produtoService.buscarPorIdBloqueante(id);
        return nonNull(produto)
                ? ResponseEntity.ok(produto)
                : ResponseEntity.notFound().build();
    }
}

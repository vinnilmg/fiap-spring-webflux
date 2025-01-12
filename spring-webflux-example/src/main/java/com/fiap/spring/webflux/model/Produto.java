package com.fiap.spring.webflux.model;

public record Produto(
        Long id,
        String nome,
        String descricao,
        Double preco
) {
}

package com.fiap.spring.webflux.service;

import com.fiap.spring.webflux.model.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProdutoService {
    private final List<Produto> produtos = new ArrayList<>();

    public ProdutoService() {
        produtos.add(new Produto(1L, "Notebook", "Notebook Dell", 3000.0));
        produtos.add(new Produto(2L, "Smartphone", "Smartphone Samsung", 1500.0));
        produtos.add(new Produto(3L, "Tablet", "Tablet Apple", 1000.0));
    }

    public List<Produto> buscarTodos() {
        return produtos;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }
}

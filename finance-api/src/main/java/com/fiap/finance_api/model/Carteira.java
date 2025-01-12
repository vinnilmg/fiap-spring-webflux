package com.fiap.finance_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carteiras")
public class Carteira {
    @Id
    private String id;
    private String nome;
    private List<Acao> acoes = new ArrayList<>();

    public Carteira(String nome, List<Acao> acoes) {
        this.nome = nome;
        this.acoes.addAll(acoes);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }
}

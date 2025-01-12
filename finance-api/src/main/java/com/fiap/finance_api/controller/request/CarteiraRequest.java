package com.fiap.finance_api.controller.request;

import com.fiap.finance_api.model.Acao;

import java.util.List;

public record CarteiraRequest(String nome, List<Acao> acoes) {
}

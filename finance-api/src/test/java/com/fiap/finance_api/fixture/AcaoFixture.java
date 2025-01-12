package com.fiap.finance_api.fixture;

import com.fiap.finance_api.model.Acao;

import java.util.List;

public class AcaoFixture {

    public static List<Acao> ONE_ACAO() {
        return List.of(FULL());
    }

    public static Acao FULL() {
        final var result = new Acao("GOOG", 1000.0d);
        result.setId("1");
        return result;
    }
}

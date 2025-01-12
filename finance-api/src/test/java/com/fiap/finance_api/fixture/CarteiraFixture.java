package com.fiap.finance_api.fixture;

import com.fiap.finance_api.model.Carteira;

public class CarteiraFixture {

    public static Carteira FULL(final String nome) {
        final var result = new Carteira(nome, AcaoFixture.ONE_ACAO());
        result.setId("1");
        return result;
    }

    public static Carteira FULL() {
        return FULL("test");
    }
}

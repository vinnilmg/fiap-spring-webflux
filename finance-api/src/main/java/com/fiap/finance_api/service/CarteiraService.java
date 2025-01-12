package com.fiap.finance_api.service;

import com.fiap.finance_api.model.Acao;
import com.fiap.finance_api.model.Carteira;
import com.fiap.finance_api.repository.CarteiraRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CarteiraService {

    private final CarteiraRepository repository;

    public CarteiraService(CarteiraRepository repository) {
        this.repository = repository;
    }

    public Mono<Double> calcularRentabilidade(final String nomeCarteira) {
        return repository.findByNome(nomeCarteira)
                .map(carteira -> carteira.getAcoes()
                        .stream()
                        .mapToDouble(Acao::getPreco)
                        .sum()
                );
    }

    public Flux<Carteira> findAll() {
        return repository.findAll();
    }

    public Mono<Carteira> save(final String nomeCarteira, final List<Acao> acoes) {
        final var carteira = new Carteira(nomeCarteira, acoes);
        return repository.save(carteira);
    }
}

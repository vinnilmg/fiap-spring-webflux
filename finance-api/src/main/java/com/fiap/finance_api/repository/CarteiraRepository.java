package com.fiap.finance_api.repository;

import com.fiap.finance_api.model.Carteira;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarteiraRepository extends ReactiveMongoRepository<Carteira, String> {
    Mono<Carteira> findByNome(String nome);

    Flux<Carteira> findAll();
}

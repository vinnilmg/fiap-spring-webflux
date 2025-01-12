package com.fiap.finance_api.service;

import com.fiap.finance_api.fixture.AcaoFixture;
import com.fiap.finance_api.fixture.CarteiraFixture;
import com.fiap.finance_api.model.Acao;
import com.fiap.finance_api.repository.CarteiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarteiraServiceTest {

    @InjectMocks
    private CarteiraService service;

    @Mock
    private CarteiraRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateProfitability() {
        final var nomeCarteira = "test";
        final var carteira = CarteiraFixture.FULL(nomeCarteira);

        when(repository.findByNome(nomeCarteira))
                .thenReturn(Mono.just(carteira));

        StepVerifier.create(service.calcularRentabilidade(nomeCarteira))
                .expectNext(carteira.getAcoes().stream().mapToDouble(Acao::getPreco).sum())
                .verifyComplete();

        verify(repository).findByNome(nomeCarteira);
    }

    @Test
    void shouldFindAll() {
        final var carteiras = List.of(CarteiraFixture.FULL());
        final var expectedResult = Flux.fromIterable(carteiras);

        when(repository.findAll())
                .thenReturn(expectedResult);

        final var result = service.findAll();
        assertEquals(carteiras, result.collectList().block());

        verify(repository).findAll();
    }

    @Test
    void shouldSave() {
        final var nomeCarteira = "test";
        final var acoes = AcaoFixture.ONE_ACAO();
        final var expectedResult = CarteiraFixture.FULL();

        when(repository.save(any()))
                .thenReturn(Mono.just(expectedResult));

        StepVerifier.create(service.save(nomeCarteira, acoes))
                .expectNext(expectedResult)
                .verifyComplete();

        verify(repository).save(any());
    }
}

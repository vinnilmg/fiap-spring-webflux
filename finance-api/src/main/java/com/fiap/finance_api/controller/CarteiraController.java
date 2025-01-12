package com.fiap.finance_api.controller;

import com.fiap.finance_api.controller.request.CarteiraRequest;
import com.fiap.finance_api.model.Carteira;
import com.fiap.finance_api.service.CarteiraService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    private final CarteiraService service;

    public CarteiraController(CarteiraService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Carteira> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<Carteira> save(@RequestBody final CarteiraRequest request) {
        return service.save(request.nome(), request.acoes());
    }

    @GetMapping("/{nomeCarteira}/rentabilidade")
    public Mono<ResponseEntity<Double>> calcularRentabilidade(@PathVariable final String nomeCarteira) {
        return service.calcularRentabilidade(nomeCarteira)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Carteira>> getCarteiraEvents() {
        final Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        final Flux<Carteira> events = service.findAll();
        return Flux.zip(interval, events);
    }
}

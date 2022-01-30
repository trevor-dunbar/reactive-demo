package com.reactivepractice.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;

@RestController
public class SpeechFluxExample {
    WebClient webClient = WebClient.create();

    @GetMapping("/speech/stream")
    public Flux<String> getSpeech() {
        return webClient.get()
                .uri("http://localhost:8081/speech")
                .exchangeToFlux(s -> s.bodyToFlux(String.class))
                .doOnEach(Signal::get);
    }
}

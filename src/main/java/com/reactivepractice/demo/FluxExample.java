package com.reactivepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class FluxExample {

    private final static Logger log = LoggerFactory.getLogger(FluxExample.class);

    WebClient webClient = WebClient.create();

    @GetMapping("/stream")
    public void streamExternalService(){
        log.info("Streaming star wars: ");
        webClient.get()
                .uri("http://localhost:8081/stream-starwars")
                .exchangeToFlux((clientResponse) -> clientResponse.bodyToFlux(String.class))
                .doOnEach((streamedWord) -> log.info(streamedWord.get()))
                .doOnComplete(() -> log.info("Done"))
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .subscribe();
    }
}

package com.reactivepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveExample {

    private final static Logger log = LoggerFactory.getLogger(ReactiveExample.class);

    WebClient webClient = WebClient.create();


    public Mono<String> callNonBlockingExternal() {

        return webClient
                .method(HttpMethod.GET)
                .uri("http://localhost:8081/hello")
                .exchangeToMono(this::handleExternalResponse)
                .doOnSuccess((str) -> {
                    log.info("Success");
                    log.info(str);
                })
                .doOnError(throwable -> log.error(throwable.getMessage()));
    }

    public Mono<String> handleExternalResponse(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class);
    }


    @GetMapping("/reactive")
    public void makeNonBlockingExternalCall_logWhenThreadIsFree(){
        callNonBlockingExternal().subscribe();
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
        log.info("I can still do work even though we're waiting!");
    }
}

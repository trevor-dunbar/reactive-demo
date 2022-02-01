package com.reactivepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RestController
public class ReactiveExample {

    private final static Logger log = LoggerFactory.getLogger(ReactiveExample.class);

    WebClient webClient = WebClient.create();

    @GetMapping("/nonblocking")
    public void makeNonBlockingExternalCall_logWhenThreadIsFree() throws InterruptedException {

        callNonBlockingExternal().subscribe();

        logFreeThread();
    }

    public Mono<String> callNonBlockingExternal() {
        log.info("making reactive call to /hello");
        return webClient
                .method(HttpMethod.GET)
                .uri("http://localhost:8081/hello")
                .exchangeToMono((clientResponse -> clientResponse.bodyToMono(String.class)))
                .doOnSuccess(log::info)
                .doOnError(throwable -> log.error(throwable.getMessage()));
    }

    private void logFreeThread() throws InterruptedException {
        for (int i = 1; i <= 6; i++) {
            TimeUnit.SECONDS.sleep(1);
            log.info("Even though we're waiting for a response, this thread is free");
        }
    }
}

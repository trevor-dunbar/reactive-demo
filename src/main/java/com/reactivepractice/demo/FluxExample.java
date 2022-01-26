package com.reactivepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@RestController
public class FluxExample {

    private final static Logger log = LoggerFactory.getLogger(FluxExample.class);

    WebClient webClient = WebClient.create();

    @GetMapping("/flux")
    public void streamExternalService(){
        webClient.get()
                .uri("http://localhost:8081/streamHello")
                .exchangeToFlux(this::handleFluxResponse)
                .doOnEach((dataObjectSignal) -> {
                    DataObject dataObjectResponse = dataObjectSignal.get();
                    log.info(String.valueOf(dataObjectResponse));
                })
                .doOnComplete(() -> {
                    log.info("Done");
                })
                .doOnError(throwable -> log.error(throwable.getMessage()));

    }

    private Flux<DataObject> handleFluxResponse(ClientResponse clientResponse){
        return clientResponse.bodyToFlux(DataObject.class);
    }
}

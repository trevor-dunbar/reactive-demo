package com.reactivepractice.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImperativeExample {

    private static final Logger log = LoggerFactory.getLogger(ImperativeExample.class);

    @GetMapping("/imperative")
    private String callExternalService_blocking(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, HttpEntity.EMPTY, String.class);

        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");
        log.info("I had to wait for the external call to finish :(");

        return response.getBody();
    }
}

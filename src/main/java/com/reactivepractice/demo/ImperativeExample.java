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

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/blocking")
    public String callExternalService_blocking() {

        log.info("Making imperative call to /hello");
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, HttpEntity.EMPTY, String.class);

        log.info("We had to wait for the external call to finish");

        log.info(response.getBody());
        return response.getBody();
    }
}

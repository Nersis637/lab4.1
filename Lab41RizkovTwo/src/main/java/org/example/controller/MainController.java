package org.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/sendRequest")
    public ResponseEntity<String> sendRequest() {
        String requestId = restTemplate.postForObject("http://localhost:8081/second/processDoctor", null, String.class);
        return ResponseEntity.ok(requestId);
    }

    @GetMapping("/checkStatus/{requestId}")
    public ResponseEntity<String> checkStatus(@PathVariable String requestId) {
        String status = restTemplate.getForObject("http://localhost:8081/second/status/" + requestId, String.class);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/getResult/{requestId}")
    public ResponseEntity<String> getResult(@PathVariable String requestId) {
        String result = restTemplate.getForObject("http://localhost:8081/second/result/" + requestId, String.class);
        return ResponseEntity.ok(result);
    }
}


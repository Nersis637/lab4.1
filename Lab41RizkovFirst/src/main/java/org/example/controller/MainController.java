package org.example.controller;

import org.example.entity.RequestStatus;
import org.example.repository.RequestStatusRepository;
import org.example.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/second")
public class MainController {
    @Autowired
    private AsyncService asyncService;

    @Autowired
    private RequestStatusRepository repository;

    @PostMapping("/processDoctor")
    public ResponseEntity<String> processRequest() {
        String requestId = UUID.randomUUID().toString();
        RequestStatus status = new RequestStatus();
        status.setRequestId(requestId);
        status.setStatus("IN_PROGRESS");
        repository.save(status);

        // Асинхронная обработка запроса
        asyncService.processRequest(requestId);

        return ResponseEntity.ok(requestId);
    }

    @GetMapping("/status/{requestId}")
    public String checkStatus(@PathVariable String requestId) {
        RequestStatus status = repository.findByRequestId(requestId);
        return status.getStatus();
    }
    @GetMapping("/result/{requestId}")
    public ResponseEntity<String> getResult(@PathVariable String requestId) {
        RequestStatus status = repository.findByRequestId(requestId);
        if (status == null || !"COMPLETED".equals(status.getStatus())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(status.getResult());
    }
}

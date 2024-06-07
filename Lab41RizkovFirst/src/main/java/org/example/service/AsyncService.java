package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.RequestStatus;
import org.example.repository.RepoDoctor;
import org.example.repository.RequestStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.example.entity.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class AsyncService {
    @Autowired
    private RequestStatusRepository repository;
    @Autowired
    private RepoDoctor doctorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Async
    public CompletableFuture<Void> processRequest(String requestId) {
        try {

            Thread.sleep(10000);
            List<Doctor> doctors = doctorRepository.findAll();
            String doctorsJson = objectMapper.writeValueAsString(doctors);
            RequestStatus status = repository.findByRequestId(requestId);
            status.setStatus("COMPLETED");
            status.setResult(doctorsJson);
            repository.save(status);
        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }
}

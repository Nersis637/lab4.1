package org.example.repository;

import org.example.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
    RequestStatus findByRequestId(String requestId);
}

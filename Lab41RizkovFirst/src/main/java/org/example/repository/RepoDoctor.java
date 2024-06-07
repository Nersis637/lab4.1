package org.example.repository;


import org.example.entity.Doctor;
import org.example.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDoctor extends JpaRepository<Doctor, Long> {

}

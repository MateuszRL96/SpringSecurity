package com.example.qualifications.repo;

import com.example.qualifications.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<Qualification,Long> {
    Optional<Qualification> findOrderByOrders(String orders);
    Optional<Qualification> findOrderByUuid(String uuid);
    List<Qualification> findOrderByClient(String client);
}


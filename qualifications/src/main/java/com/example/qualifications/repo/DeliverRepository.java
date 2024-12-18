package com.example.qualifications.repo;

import com.example.qualifications.entity.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliverRepository extends JpaRepository<Deliver,Long> {
    Optional<Deliver> findByUuid(String uuid);
}


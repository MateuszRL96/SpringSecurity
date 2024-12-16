package com.example.koszyk.repo;

import com.example.koszyk.entity.Koszyk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KoszykRepo extends JpaRepository<Koszyk, Long> {
    Optional<Koszyk> findByUuid(String uuid);
}

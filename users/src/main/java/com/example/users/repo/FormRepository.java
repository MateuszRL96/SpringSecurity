package com.example.users.repo;

import com.example.users.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}

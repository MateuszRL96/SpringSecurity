package com.example.main.repo;

import com.example.main.entity.qualification.Qualifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QualificationRepository extends JpaRepository<Qualifications, Long> {

    List<Qualifications> findAllQualifications(String name);

}

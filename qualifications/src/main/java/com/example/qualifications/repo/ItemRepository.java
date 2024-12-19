package com.example.qualifications.repo;

import com.example.qualifications.entity.Qualification;
import com.example.qualifications.entity.QualificationItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ItemRepository extends JpaRepository<QualificationItems,Long> {

    List<QualificationItems> findQualificationItemsByQualification(Qualification qualification);
}

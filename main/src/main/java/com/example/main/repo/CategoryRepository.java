package com.example.main.repo;

import com.example.main.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByShortId(String shortId);
    Optional<Category> findByName(String name);
}

package com.example.main.repo;

import com.example.main.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(nativeQuery = true,value = "SELECT count(*) from product where active is true")
    long countActiveProducts();

    List<ProductEntity> findByNameAndCreateAt(String name, LocalDate createAt);
}

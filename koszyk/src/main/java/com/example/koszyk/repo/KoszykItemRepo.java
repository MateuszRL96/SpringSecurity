package com.example.koszyk.repo;
import com.example.koszyk.entity.Koszyk;
import com.example.koszyk.entity.KoszykItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KoszykItemRepo extends JpaRepository<KoszykItems, Long> {

    Optional<KoszykItems> findByKoszykAndProduct(Koszyk koszyk, String product);

    @Query(nativeQuery = true ,value = "SELECT SUM(quantity) from basket_items where basket = ?1")
    Long sumKoszykItems(long basket);
    Optional<KoszykItems> findKoszykItemsByProductAndKoszyk(String uuid,Koszyk koszyk);
    List<KoszykItems> findKoszykItemsByKoszyk(Koszyk koszyk);


}

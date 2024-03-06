package com.example.main.repo;

import com.example.main.entity.people.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {

    List<People> findSomeone(long id);

    @Query(nativeQuery = true,value = "SELECT * from people_table where id IS NOT NULL")
    List<People> personsWithSomeTechnologiesKnows();

    List<People> findById(long id);
}

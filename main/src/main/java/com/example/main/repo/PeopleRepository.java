package com.example.main.repo;

import com.example.auth.entity.User;
import com.example.main.entity.people.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<User, Long> {

    List<People> findByActiveTrue();
    List<People> findById(long id);
}

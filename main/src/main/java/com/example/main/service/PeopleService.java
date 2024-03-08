package com.example.main.service;

import com.example.main.entity.people.People;
import com.example.main.entity.qualification.Technologies;
import com.example.main.exceptions.PersonNotFoundException;
import com.example.main.repo.PeopleRepository;
import com.example.main.repo.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<People> getActivePeople(){
        return peopleRepository.findByActiveTrue();
    }

    public List<People> getPeopleWithId(long personId){
        return peopleRepository.findById(personId);
    }


}

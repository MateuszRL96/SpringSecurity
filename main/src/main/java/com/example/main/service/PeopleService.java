package com.example.main.service;

import com.example.main.entity.people.People;
import com.example.main.entity.qualification.Technologies;
import com.example.main.repo.PeopleRepository;
import com.example.main.repo.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final QualificationRepository qualificationRepository;

    public List<People> getPeople(){
        return peopleRepository.findAll();
    }

    public Optional<People> findPeopleWithSomeTechnologies(Long personId, List<Technologies> stackTechnologiesKnown){
         return peopleRepository.findById(personId);
    }
}

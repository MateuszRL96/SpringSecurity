package com.example.main.fasada;


import com.example.main.entity.people.People;
import com.example.main.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/active")
    public ResponseEntity<List<People>> getActivePeople() {
        List<People> activePeople = peopleService.getActivePeople();
        return ResponseEntity.ok(activePeople);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<People>> getPersonById(@PathVariable Long id) {
        List<People> person = peopleService.getPeopleWithId(id);
        return ResponseEntity.ok(person);
    }



}

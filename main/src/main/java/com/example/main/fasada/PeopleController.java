package com.example.main.fasada;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/people")
@RequiredArgsConstructor
public class PeopleController {
}

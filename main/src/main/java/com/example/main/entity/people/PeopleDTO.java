package com.example.main.entity.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PeopleDTO extends People{

    public PeopleDTO(long id, String name, String lastName, String address, int age, String phone) {
        super(id, name, lastName, address, age, phone);
    }
}

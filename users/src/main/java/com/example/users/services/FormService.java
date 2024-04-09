package com.example.users.services;

import com.example.users.entity.Form;
import com.example.users.repo.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    private final FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    // Metoda do generowania rekomendacji na podstawie odpowiedzi
}

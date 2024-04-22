package com.example.main.service;

import com.example.main.entity.Form;
import com.example.main.repo.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    // Metoda do generowania rekomendacji na podstawie odpowiedzi
}

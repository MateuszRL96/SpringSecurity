package com.example.users.controller;

import com.example.users.entity.Form;
import com.example.users.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class FormController {
    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public ResponseEntity<List<Form>> getAllForms() {
        List<Form> forms = formService.getAllForms();
        return ResponseEntity.ok(forms);
    }

    // Endpoint do przesyłania odpowiedzi i otrzymywania rekomendacji
}


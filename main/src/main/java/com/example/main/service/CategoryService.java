package com.example.main.service;

import com.example.main.entity.Category;
import com.example.main.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
}


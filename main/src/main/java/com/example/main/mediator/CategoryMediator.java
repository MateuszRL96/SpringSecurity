package com.example.main.mediator;

import com.example.main.entity.category.CategoryDTO;
import com.example.main.exceptions.ObjectExistInDBException;
import com.example.main.service.CategoryService;
import com.example.main.translator.CategoryToCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMediator {

    private final CategoryToCategoryDTO categoryToCategoryDTO;
    private final CategoryService categoryService;

    public ResponseEntity<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryService.getCategory().forEach(value->{
            categoryDTOS.add(categoryToCategoryDTO.toCategoryDTO(value));
        });
        return ResponseEntity.ok(categoryDTOS);
    }

    public void createCategory(CategoryDTO categoryDTO) throws ObjectExistInDBException {
        categoryService.create(categoryDTO);
    }
}



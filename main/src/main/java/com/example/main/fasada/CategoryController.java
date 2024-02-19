package com.example.main.fasada;

import com.example.main.entity.dto.CategoryDTO;
import com.example.main.mediator.CategoryMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
public class CategoryController {

    private final CategoryMediator categoryMediator;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getCategory(){
        return categoryMediator.getCategory();
    }
}

package com.example.main.translator;

import com.example.main.entity.category.Category;
import com.example.main.entity.category.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper
public abstract class CategoryToCategoryDTO {

    public CategoryDTO toCategoryDTO(Category category){
        return  translateToCategoryDTO(category);
    }

    @Mappings({})
    protected abstract CategoryDTO translateToCategoryDTO(Category category);
}



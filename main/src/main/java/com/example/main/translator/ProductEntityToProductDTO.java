package com.example.main.translator;

import com.example.main.entity.Category;
import com.example.main.entity.ProductEntity;
import com.example.main.entity.dto.CategoryDTO;
import com.example.main.entity.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public abstract class ProductEntityToProductDTO {

    public ProductDTO toProductDTO(ProductEntity productEntity){
        return toDTO(productEntity);
    }

    @Mappings({
            @Mapping(expression = "java(toCategoryDTO(productEntity.getCategory()))",target = "categoryDTO")
    })
    protected abstract ProductDTO toDTO(ProductEntity productEntity);

    @Mappings({})
    protected abstract CategoryDTO toCategoryDTO(Category category);
}


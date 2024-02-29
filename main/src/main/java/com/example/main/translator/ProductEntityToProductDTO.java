package com.example.main.translator;

import com.example.main.entity.category.Category;
import com.example.main.entity.product.ProductEntity;
import com.example.main.entity.category.CategoryDTO;
import com.example.main.entity.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper
@Component
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


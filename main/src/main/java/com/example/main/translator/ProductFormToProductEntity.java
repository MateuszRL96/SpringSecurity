package com.example.main.translator;

import com.example.main.entity.Category;
import com.example.main.entity.ProductEntity;
import com.example.main.entity.ProductFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class ProductFormToProductEntity {

    public ProductEntity toProductEntity(ProductFormDTO productFormDTO){
        return translate(productFormDTO);
    }


    @Mappings({
            @Mapping(expression = "java(translate(productFormDTO.getCategory()))", target = "category"),
            @Mapping(target = "imageUrls",source = "imagesUuid")
    })
    protected abstract ProductEntity translate(ProductFormDTO productFormDTO);


    protected Category translate(String uuid){
        Category category = new Category();
        category.setShortId(uuid);
        return category;
    }


}


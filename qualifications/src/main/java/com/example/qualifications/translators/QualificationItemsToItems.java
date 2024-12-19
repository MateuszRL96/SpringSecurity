package com.example.qualifications.translators;

import com.example.qualifications.entity.Items;
import com.example.qualifications.entity.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public abstract class QualificationItemsToItems {
    public Items toItems(Qualification qualificationItems){
        return translate(qualificationItems);
    }


    @Mappings({
            @Mapping(target = "imageUrl", ignore = true),
            @Mapping(target = "price", source ="priceUnit"),
            @Mapping(target = "summaryPrice", source ="priceSummary"),
    })
    protected abstract Items translate(Qualification qualificationItems);
}


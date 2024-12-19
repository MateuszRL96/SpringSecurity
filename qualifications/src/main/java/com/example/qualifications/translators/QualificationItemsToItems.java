package com.example.qualifications.translators;

import com.example.qualifications.entity.Items;
import com.example.qualifications.entity.QualificationItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public class QualificationItemsToItems {
    public static Items toItems(QualificationItems qualificationItems){
        return translate(qualificationItems);
    }


    @Mappings({
            @Mapping(target = "imageUrl", ignore = true),
            @Mapping(target = "price", source ="priceUnit"),
            @Mapping(target = "summaryPrice", source ="priceSummary"),
    })
    protected abstract Items translate(QualificationItems qualificationItems);
}


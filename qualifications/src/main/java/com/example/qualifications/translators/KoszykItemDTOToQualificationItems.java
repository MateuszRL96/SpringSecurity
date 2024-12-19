package com.example.qualifications.translators;


import com.example.qualifications.entity.KoszykItemDTO;
import com.example.qualifications.entity.QualificationItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public class KoszykItemDTOToQualificationItems {
    public QualificationItems toQualificationItems(KoszykItemDTO koszykItemDTO){
        return translate(koszykItemDTO);
    }


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "product", source = "uuid"),
            @Mapping(target = "priceUnit", source = "price"),
            @Mapping(target = "priceSummary", source = "summaryPrice"),
    })
    protected QualificationItems translate(KoszykItemDTO koszykItemDTO) {
        return null;
    }
}


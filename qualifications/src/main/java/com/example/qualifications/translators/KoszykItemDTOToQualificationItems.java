package com.example.qualifications.translators;


import com.example.koszyk.entity.KoszykItems;
import com.example.qualifications.entity.KoszykItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public class KoszykItemDTOToQualificationItems {
    public KoszykItems toQualificationItems(KoszykItemDTO koszykItemDTO){
        return translate(koszykItemDTO);
    }


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "product", source = "uuid"),
            @Mapping(target = "priceUnit", source = "price"),
            @Mapping(target = "priceSummary", source = "summaryPrice"),
    })
    protected KoszykItems translate(KoszykItemDTO koszykItemDTO) {
        return null;
    }
}


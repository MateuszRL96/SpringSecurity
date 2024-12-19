package com.example.qualifications.translators;


import com.example.qualifications.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper
public abstract class QualificationToQualificationDTO {
    public QualificationDTO toQualificationDTO(Qualification qualification){
        return translate(qualification);
    }


    @Mappings({
            @Mapping(target = "customerDetails",expression = "java(translateToCustomer(qualification))"),
            @Mapping(target = "address",expression = "java(translateAddres(qualification))"),
            @Mapping(target = "deliver",expression = "java(translateDeliver(qualification.getDeliver()))"),
    })
    protected abstract QualificationDTO translate(Qualification qualification);

    @Mappings({})
    protected abstract CustomDetails translateToCustomer(Qualification qualification);

    @Mappings({})
    protected abstract Adress translateAddres(Qualification qualification);

    @Mappings({})
    protected abstract DeliverDTO translateDeliver(Deliver deliver);

}


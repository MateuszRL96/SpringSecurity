package com.example.qualifications.translators;

import com.example.qualifications.entity.Deliver;
import com.example.qualifications.entity.DeliverDTO;
import com.example.qualifications.entity.Qualification;
import com.example.qualifications.entity.QualificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public abstract class QualificationDTOToQualification {

    public static Qualification toQualification(QualificationDTO qualificationDTO){
        return translate(qualificationDTO);
    }


    @Mappings({
            @Mapping(expression = "java(qualificationDTO.getCustomerDetails().getFirstName())",target = "firstName"),
            @Mapping(expression = "java(qualificationDTO.getCustomerDetails().getLastName())",target = "lastName"),
            @Mapping(expression = "java(qualificationDTO.getCustomerDetails().getEmail())",target = "email"),
            @Mapping(expression = "java(qualificationDTO.getCustomerDetails().getPhone())",target = "phone"),
            @Mapping(expression = "java(qualificationDTO.getAddress().getCity())",target = "city"),
            @Mapping(expression = "java(qualificationDTO.getAddress().getNumber())",target = "number"),
            @Mapping(expression = "java(qualificationDTO.getAddress().getStreet())",target = "street"),
            @Mapping(expression = "java(qualificationDTO.getAddress().getPostCode())",target = "postCode"),
            @Mapping(expression = "java(translate(qualificationDTO.getDeliver()))",target = "deliver"),
    })
    protected abstract Qualification translate(QualificationDTO qualificationDTO);


    @Mappings({})
    protected abstract Deliver translate(DeliverDTO deliverDTO);
}


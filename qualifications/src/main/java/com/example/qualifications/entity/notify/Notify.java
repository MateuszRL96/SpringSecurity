package com.example.qualifications.entity.notify;

import com.example.qualifications.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Notify {
    private Qualification qualification;
    private String localReceiptDateTime;
    private List<Property> properties;

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getLocalReceiptDateTime() {
        return localReceiptDateTime;
    }

    public void setLocalReceiptDateTime(String localReceiptDateTime) {
        this.localReceiptDateTime = localReceiptDateTime;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}


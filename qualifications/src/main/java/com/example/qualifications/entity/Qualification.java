package com.example.qualifications.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Qualification{
    @Id
    @GeneratedValue(generator = "orders_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_id_seq",sequenceName = "orders_id_seq", allocationSize = 1)
    private long id;
    private String uuid;
    private String qualification;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String street;
    private String number;
    @Column(name = "postcode")
    private String postCode;
    private String client;
    @ManyToOne
    @JoinColumn(name = "deliver")
    private Deliver deliver;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getQualification() {
        return qualification;
    }

    public static void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Status getStatus() {
        return status;
    }

    public static void setStatus(Status status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Deliver getDeliver() {
        return deliver;
    }

    public static void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }
}


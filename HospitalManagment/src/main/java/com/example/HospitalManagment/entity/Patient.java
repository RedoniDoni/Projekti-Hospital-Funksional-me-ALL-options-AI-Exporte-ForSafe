package com.example.HospitalManagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient extends DataEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "street")
    private String street;

    @Column(name = "age")
    private Long age;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name ="room_id",nullable = false)
    private Room room;
    //this is to take a list of patient related with one appoitment --if we need
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

//    @OneToMany(mappedBy = "inventory")
//    private List<InventoryRequest> requests;

}

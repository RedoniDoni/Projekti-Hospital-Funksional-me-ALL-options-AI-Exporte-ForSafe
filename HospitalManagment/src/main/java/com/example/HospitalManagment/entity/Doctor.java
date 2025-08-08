package com.example.HospitalManagment.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="doctors")
public class Doctor extends DataEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Long age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",nullable = false)
    private Department departament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id",nullable = false)
    private City city;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

//    @OneToMany(mappedBy = "inventory")
//    private List<InventoryRequest> requests;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacation> vacations = new ArrayList<>();
}

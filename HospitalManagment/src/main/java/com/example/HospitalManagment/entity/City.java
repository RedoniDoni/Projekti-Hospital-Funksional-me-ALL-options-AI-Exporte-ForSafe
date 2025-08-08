package com.example.HospitalManagment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
public class City extends DataEntity{

    @Column(name = "name")
    private String name;

    //one city can have many patients
    @OneToMany(mappedBy = "city")
    List<Patient> patients;
}

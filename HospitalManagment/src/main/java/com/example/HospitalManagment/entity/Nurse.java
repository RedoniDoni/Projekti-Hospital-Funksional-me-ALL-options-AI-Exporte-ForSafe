package com.example.HospitalManagment.entity;

import com.example.HospitalManagment.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nurse")
public class Nurse extends DataEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "street")
    private String street;


    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "shift_timing")
    private String shiftTiming;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

//    @OneToMany(mappedBy = "inventory")
//    private List<InventoryRequest> requests;
}

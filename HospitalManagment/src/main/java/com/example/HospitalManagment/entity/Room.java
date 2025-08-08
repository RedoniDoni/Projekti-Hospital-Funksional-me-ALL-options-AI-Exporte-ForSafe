package com.example.HospitalManagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends DataEntity{

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "description")
    private String description;

    @Column(name = "floor")
    private Long floor;

    @Column(name = "nr_of_beeds")
    private Long nrOfBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department departament;
}

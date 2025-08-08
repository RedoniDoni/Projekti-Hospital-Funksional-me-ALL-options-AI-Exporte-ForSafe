package com.example.HospitalManagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventory_request")
@Getter
@Setter
public class InventoryRequest extends DataEntity{

    private Long quantityRequested;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = true)
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;
}

package com.example.HospitalManagment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diagnosis")
public class Diagnosis extends DataEntity{

    private String diagnosisDetails;

    private String treatmentPlan;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;  // The appointment associated with the diagnosis

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
